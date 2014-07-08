package edu.ut.mobile.network;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.util.List;
import java.lang.reflect.Method;

import symlab.ust.hk.offloading.ar.tree.IntermediateTreeNode;
import symlab.ust.hk.offloading.ar.tree.TreeManager;


public class NetworkManagerServer {
    int portnum;
    Socket mysocket = null;
    InputStream in = null;
    OutputStream out = null;
    
    ObjectInputStream ois = null;
    ObjectOutputStream oos = null;
    ServerSocket serversoc = null;
    byte[] serveraddress = new byte[4];
    long processTime;
   
    

    public NetworkManagerServer(/*byte []serveraddress, */int port) {
        //this.serveraddress = serveraddress;
        portnum = port;
    }


    public boolean makeconnection() {

        if (serversoc == null || serversoc.isClosed()) {
            try {
                serversoc = new ServerSocket(portnum);
                //serversoc.bind(new InetSocketAddress(Inet4Address.getByAddress(serveraddress), portnum));
                serversoc.setSoTimeout(0);
            } catch (IOException ex) {
            }
        }


        try {
            //System.out.println("server waiting");
            mysocket = serversoc.accept();
            
            in =  mysocket.getInputStream();
            out = mysocket.getOutputStream();

            
            //oos = new ObjectOutputStream(out);
            oos = new ObjectOutputStream(new BufferedOutputStream(out));
            oos.flush();
 
            //ois = new ObjectInputStream(in);
            ois =new ObjectInputStream(new BufferedInputStream(in));

            
            
            //oos = new ObjectOutputStream(out);
            //oos = new ObjectOutputStream(new BufferedOutputStream(mysocket.getOutputStream()));
            
            //ois = new ObjectInputStream(in);

            //System.out.println("connection established");

            waitforreceivingdata();
            return true;
        } catch (SocketException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        } catch (Exception ex) {
            return false;
        }

    }


    private void waitforreceivingdata() {
        try {
            new Receiving().waitforreceivingdata();
        } catch (Exception ex) {
        }
    }


    class Receiving implements Runnable {
        String functionName = null;
        Class[] paramTypes = null;
        Object[] paramValues = null;
        Object state = null;
        Class stateDType = null;
        Pack myPack = null;
        
        List<String> timestamps;

        public Receiving() {
        }

        public void waitforreceivingdata() {
            Thread t = new Thread(this);
            //System.out.println("Thread Starting ");
            t.start();
        }

        @Override
        public void run() {
            try { 
            	processTime = System.currentTimeMillis();
                myPack = (Pack) ois.readObject();
                functionName = myPack.getfunctionName();
                paramTypes = myPack.getparamTypes();
                paramValues = myPack.getparamValues();
                state = myPack.getstate();
                stateDType = myPack.getstateType();
                timestamps = myPack.getTimeStamps();
                
                if (functionName != null && functionName.length() > 0) {
                    try {

                    	//System.out.println("trying to load and execute");
                        Class cls = Class.forName(stateDType.getName());
                        
                        Method method = cls.getDeclaredMethod(functionName, paramTypes);
                        
                        try{
                        	
                        	timestamps.add(processTime+",server1");
                        	/*IntermediateTreeNode node;
                        	
                        	if ((node = TreeManager.getInstance().find("keyTree1"))!=null){
                        		oos.flush();
                        		
                        		
                        		ResultPack rp = new ResultPack(node.getResult().getresult(), node.getResult().getstate());
                        		oos.flush();
                        		oos.writeObject(rp);
                                //System.out.println("Existent object was wrote it");
                                oos.flush(); 
                                //System.out.println("Object sent: " + (System.currentTimeMillis() - processTime));
                        		
                        	}else{*/
                        		oos.flush();
                        		Object result = method.invoke(state, paramValues);
                        		ResultPack rp = new ResultPack(result, state);
                        		rp.setTimeStamps(timestamps);
                        		 
                        		
                        		//System.out.println("Size in bytes: " + sizeInBytes(rp));
                        		oos.flush();
                        		oos.writeObject(rp);
                        		//System.out.println("Object wrote it");
                        		oos.flush(); 
                        		//System.out.println("Object executed and flushed: " + (System.currentTimeMillis() - processTime));
                        		
                        		/*if (TreeManager.getInstance().isEmpty()){
                					TreeManager.getInstance().setRoot(new IntermediateTreeNode("keyTree1", 
                							new ResultPack(rp.getresult(), rp.getstate()))
                							);
                				}else{
                					TreeManager.getInstance().getRoot().addChild(new IntermediateTreeNode("keyTree2", 
                							new ResultPack(rp.getresult(), rp.getstate()))
                							);
                				}*/
                            
                        	//}
                        	
                        	
                          
                        } catch (IllegalAccessException ex) {
                            returnnull(oos);
                            System.out.println("Hubo problema 1");
                        } catch (InvocationTargetException ex) {
                            returnnull(oos);
                            System.out.println("Hubo problema 2");
                        } catch(Exception ex){
                            ResultPack rp = new ResultPack(null, state);
                            oos.writeObject(rp);
                            oos.flush();
                            System.out.println("Hubo problema 3");
                        }
                        
                    } catch (ClassNotFoundException ex) {
                        returnnull(oos);
                        System.out.println("Hubo problema 4");
                    }  catch (IllegalArgumentException ex) {
                        returnnull(oos);
                        System.out.println("Hubo problema 5");
                    } catch (NoSuchMethodException ex) {
                        returnnull(oos);
                        System.out.println("Hubo problema 6");
                    } catch (SecurityException ex) {
                        returnnull(oos);
                        System.out.println("Hubo problema 7");
                    } finally {

                    	//oos.reset();
                    	//ois.reset();
                    	
                        oos.close();
                        ois.close();

                        in.close();
                        out.close();

                        mysocket.close();

                        oos = null;
                        ois = null;

                        in = null;
                        out = null;
                        mysocket = null;

                    }
                } else {
                    returnnull(oos);
                }
            } catch (IOException ex) {
                returnnull(oos);
                System.out.println("Hubo problema 8");
            } catch (ClassNotFoundException ex) {
                returnnull(oos);
                System.out.println("Hubo problema 9");
                ex.printStackTrace();
            } finally {
                makeconnection();
            }
        }
    }

    void returnnull(ObjectOutputStream oos){
        if(oos != null)
            try {
                oos.writeObject(null);
                oos.flush();
            } catch (IOException ex1) {

            }
    }
    
    public static int sizeInBytes(Object obj) throws java.io.IOException  
    {  
        ByteArrayOutputStream byteObject = new ByteArrayOutputStream();  
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteObject);  
        objectOutputStream.writeObject(obj);  
        objectOutputStream.flush();  
        objectOutputStream.close();  
        byteObject.close();  
      
        return byteObject.toByteArray().length;  
    }  


}

