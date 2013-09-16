/**
 * Your application code goes here
 */
package userclasses;

import com.codename1.components.InfiniteProgress;
import com.codename1.contacts.ContactsManager;
import com.codename1.contacts.ContactsModel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.processing.Result;
import generated.StateMachineBase;
import com.codename1.ui.*;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.util.Resources;
import com.codename1.util.StringUtil;
import com.imedia.bulksms.BulkSMSUsers;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;
//import java.util.concurrent.RunnableFuture;
//import javax.sound.midi.Soundbank;
//import sun.security.util.Password;

/**
 *
 * @author Agada Godwin C.
 */
public class StateMachine extends StateMachineBase {

    Vector<Hashtable> myFamilyGroup;
    Vector<Hashtable> myFriendsGroup;
    Vector<Hashtable> myCoWorkersGroup;
    Vector<Hashtable> userList;
    Hashtable<String, String> userInfo;
    Hashtable loggedInUser;
    Vector<String> phoneNumbers;
    BulkSMSUsers bulkSMSUser;
    private String myAppId;
    private String resApiKey;
    private String group;
    private String status;
    private String smsResponse;
    private String smsToSend;
    private String numberOfCredit;

    public StateMachine(String resFile) {
        super(resFile);
        // do not modify, write code in initVars and initialize class members there,
        // the constructor might be invoked too late due to race conditions that might occur
    }

    /**
     * this method should be used to initialize variables instead of the
     * constructor/class scope to avoid race conditions
     */
    @Override
    protected void initVars(Resources res) {
        myAppId = "sarqGjcQUJdsLvj87hvGR5yIUxQvperLwDqs7Dav";
        resApiKey = "x9G5apUmyKronu8vsEph94vMTfWQUFnJ965eGTS3";
    }

    public void registerAppUser(String username, String password, String email) {

        Hashtable data = new Hashtable();
        data.put("username", username);
        data.put("password", password);
        data.put("credits", "3");
        data.put("email", email);

        final String json = Result.fromContent(data).toString();

        final ConnectionRequest request = new ConnectionRequest() {
            @Override
            protected void buildRequestBody(OutputStream os) throws IOException {
                os.write(json.getBytes("UTF-8"));
            }

            // **************** Get the status of the connection        
            @Override
            protected void readHeaders(Object connection) throws IOException {

                status = getHeader(connection, "status");
                // System.out.println("The status of the connection: " + status);
            }
            //*****************

            @Override
            protected void readResponse(InputStream input) throws IOException {

                JSONParser p = new JSONParser();
                InputStreamReader inp = new InputStreamReader(input);
                Hashtable h = p.parse(inp);
                // userInfo = (Vector) h.get("results");
                userInfo = new Hashtable<String, String>();
                userInfo.put("sessionString", h.get("sessionToken").toString());
                userInfo.put("objectId", h.get("objectId").toString());
                //userInfo.put("email", h.get("email").toString());

                //System.out.println("tracking the issue : " + userInfo.toString());


            }
        };

        final NetworkManager manager = NetworkManager.getInstance();
        Command cancel = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                manager.killAndWait(request);
                //do Option2
            }
        };

        InfiniteProgress ip = new InfiniteProgress();
        Dialog dlg = ip.showInifiniteBlocking();
        dlg.setBackCommand(cancel);

        String url = "https://api.parse.com/1/users";
        request.setUrl(url);
        request.setContentType("application/json");
        request.addRequestHeader("X-Parse-Application-Id", myAppId);
        request.addRequestHeader("X-Parse-REST-API-Key", resApiKey);
        request.setFailSilently(true);//stops user from seeing error message on failure
        request.setPost(true);
        //request.setTimeout(3000);
        request.setDuplicateSupported(true);
        request.setDisposeOnCompletion(dlg);


        //NetworkManager manager = NetworkManager.getInstance();
        manager.start();

        manager.addToQueueAndWait(request);
    }

    private String encodePWD(String password) {
        String text = com.codename1.util.Base64.encode(password.getBytes());
        String pwd = com.codename1.util.Base64.encode(text.getBytes());

        return pwd;
    }

    private void userLogin(String username, String password) {

        final ConnectionRequest request = new ConnectionRequest() {
            // **************** Get the status of the connection        
            @Override
            protected void readHeaders(Object connection) throws IOException {

                status = getHeader(connection, "status");

                // System.out.println("The status of the connection: " + status);
            }
            //*****************

            @Override
            protected void readResponse(InputStream input) throws IOException {
                //status = String.valueOf(getResponseCode());

                JSONParser p = new JSONParser();
                InputStreamReader inp = new InputStreamReader(input);
                Hashtable h = p.parse(inp);
                loggedInUser = new Hashtable();
                loggedInUser.put("sessionString", h.get("sessionToken").toString());
                loggedInUser.put("objectId", h.get("objectId").toString());
                numberOfCredit = h.get("credits").toString();
                loggedInUser.put("username", h.get("username").toString());
                loggedInUser.put("email", h.get("email").toString());
                // System.out.println(loggedInUser.toString());

            }
        };


        final NetworkManager manager = NetworkManager.getInstance();
        Command cancel = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                manager.killAndWait(request);
                //do Option2
            }
        };

        InfiniteProgress ip = new InfiniteProgress();
        Dialog dlg = ip.showInifiniteBlocking();
        dlg.setBackCommand(cancel);

        String url = "https://api.parse.com/1/login";
        request.setUrl(url);
        request.setContentType("application/json");
        request.addRequestHeader("X-Parse-Application-Id", myAppId);
        request.addRequestHeader("X-Parse-REST-API-Key", resApiKey);

        request.setFailSilently(true);//stops user from seeing error message on failure
        request.setPost(false);
        //request.setHttpMethod("PUT");
        request.addArgument("username", username);
        request.addArgument("password", password);
        request.setDuplicateSupported(true);
        request.setDisposeOnCompletion(dlg);

        //NetworkManager manager = NetworkManager.getInstance();
        manager.start();
        manager.addToQueueAndWait(request);
    }

    private void pleaseResetMyPassword(String email) {
        Hashtable data = new Hashtable();
        data.put("email", email);

        final String json = Result.fromContent(data).toString();

        final ConnectionRequest request = new ConnectionRequest() {
            @Override
            protected void buildRequestBody(OutputStream os) throws IOException {
                os.write(json.getBytes("UTF-8"));
            }

            // **************** Get the status of the connection        
            @Override
            protected void readHeaders(Object connection) throws IOException {

                status = getHeader(connection, "status");
                // System.out.println("The status of the connection: " + status);
            }
            //*****************

            @Override
            protected void readResponse(InputStream input) throws IOException {

                JSONParser p = new JSONParser();
                InputStreamReader inp = new InputStreamReader(input);
                Hashtable h = p.parse(inp);

                // System.out.println("The response body : " + h.toString());
            }
        };

        final NetworkManager manager = NetworkManager.getInstance();
        Command cancel = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                manager.killAndWait(request);
                //do Option2
            }
        };

        InfiniteProgress ip = new InfiniteProgress();
        Dialog dlg = ip.showInifiniteBlocking();
        dlg.setBackCommand(cancel);

        String url = "https://api.parse.com/1/requestPasswordReset";
        request.setUrl(url);
        request.setContentType("application/json");
        request.addRequestHeader("X-Parse-Application-Id", myAppId);
        request.addRequestHeader("X-Parse-REST-API-Key", resApiKey);
        request.setFailSilently(true);//stops user from seeing error message on failure
        request.setPost(true);
        //request.setTimeout(3000);
        request.setDuplicateSupported(true);
        request.setDisposeOnCompletion(dlg);


        //NetworkManager manager = NetworkManager.getInstance();
        manager.start();

        manager.addToQueueAndWait(request);
    }

    public void searchFor(String username, String email) {

        final ConnectionRequest request = new ConnectionRequest() {
            // **************** Get the status of the connection        
            @Override
            protected void readHeaders(Object connection) throws IOException {

                status = getHeader(connection, "status");

                //System.out.println("The status of the connection: " + status);
            }
            //*****************

            @Override
            protected void readResponse(InputStream input) throws IOException {

                JSONParser p = new JSONParser();
                InputStreamReader inp = new InputStreamReader(input);
                Hashtable h = p.parse(inp);

                //final Vector v = (Vector) h.get("results");
                //System.out.println("--------------------------------------------------");
                //contactList.clear();
                userList = (Vector) h.get("results");
                //System.out.println(userList.toString());

            }
        };


        //String searchText = findSearchText(c.getComponentForm()).getText();

//        Hashtable data1 = new Hashtable();
//        data1.put("$options", "im");
//        //data1.put("$regex", username);
//        data1.put("username", username);

        Hashtable data2 = new Hashtable();
        data2.put("username", username);

        Hashtable data3 = new Hashtable();
        data3.put("email", email);

        Vector<Hashtable> toSearch = new Vector<Hashtable>();
        toSearch.addElement(data2);
        toSearch.addElement(data3);

        Hashtable data = new Hashtable();
        data.put("$or", toSearch);

        String json = Result.fromContent(data).toString();

        //System.out.println(json);

        final NetworkManager manager = NetworkManager.getInstance();
        Command cancel = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                manager.killAndWait(request);
                //do Option2
            }
        };

        InfiniteProgress ip = new InfiniteProgress();
        Dialog dlg = ip.showInifiniteBlocking();
        dlg.setBackCommand(cancel);


        String url = "https://api.parse.com/1/users";
        request.setUrl(url);
        request.setContentType("application/json");
        request.addRequestHeader("X-Parse-Application-Id", myAppId);
        request.addRequestHeader("X-Parse-REST-API-Key", resApiKey);

        request.setFailSilently(true);//stops user from seeing error message on failure
        request.setPost(false);
        request.addArgument("where", json);
        //request.addArgument("limit", "40");
        request.setDuplicateSupported(true);
        //request.setDisposeOnCompletion(progress);
        request.setDisposeOnCompletion(dlg);
        //progress.show();

        //NetworkManager manager = NetworkManager.getInstance();
        manager.start();
        manager.addToQueueAndWait(request);
    }
    Thread checkCredit = new Thread(new Runnable() {
        String status1;
        Hashtable<String, String> loggedInUser = new Hashtable<String, String>();

        public void run() {

            // System.out.println("******** About to run thread ************");

            final ConnectionRequest request = new ConnectionRequest() {
                // **************** Get the status of the connection        
                @Override
                protected void readHeaders(Object connection) throws IOException {

                    status1 = getHeader(connection, "status");

                    //System.out.println("The status of the connection: " + status);
                }
                //*****************

                @Override
                protected void readResponse(InputStream input) throws IOException {

                    JSONParser p = new JSONParser();
                    InputStreamReader inp = new InputStreamReader(input);
                    Hashtable<String, String> h = new Hashtable<String, String>();
                    h = p.parse(inp);
                    numberOfCredit = h.get("credits").toString();
                    //  loggedInUser = p.parse(inp);
                }
            };


            String url = "https://api.parse.com/1/login";
            request.setUrl(url);
            request.setContentType("application/json");
            request.addRequestHeader("X-Parse-Application-Id", myAppId);
            request.addRequestHeader("X-Parse-REST-API-Key", resApiKey);

            request.setFailSilently(true);//stops user from seeing error message on failure
            request.setPost(false);
            //request.setHttpMethod("PUT");
            request.addArgument("username", bulkSMSUser.getUsername());
            request.addArgument("password", bulkSMSUser.getPassword());
            request.setDuplicateSupported(true);

            NetworkManager manager = NetworkManager.getInstance();
            manager.start();
            manager.addToQueueAndWait(request);

//                if ((status1 != null) && ("200 OK".equals(status1))) {
//                 System.out.println("user logged on : " + loggedInUser.toString());
//                    System.out.println("Session token : "+ bulkSMSUser.getSessionToken());
//                numberOfCredit = loggedInUser.get("credits").toString();
//            }

        }
    });

    private void checkMyCredit() {

        // System.out.println("******** About to run thread ************");

        final ConnectionRequest request = new ConnectionRequest() {
            // **************** Get the status of the connection        
            @Override
            protected void readHeaders(Object connection) throws IOException {

                status = getHeader(connection, "status");

                //System.out.println("The status of the connection: " + status);
            }
            //*****************

            @Override
            protected void readResponse(InputStream input) throws IOException {

                JSONParser p = new JSONParser();
                InputStreamReader inp = new InputStreamReader(input);
                loggedInUser = p.parse(inp);

                //loggedInUser = (Vector) h.get("results");

                // System.out.println("user logged on : " + loggedInUser.toString());

            }
        };


        final NetworkManager manager = NetworkManager.getInstance();
        Command cancel = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                manager.killAndWait(request);
                //do Option2
            }
        };

        InfiniteProgress ip = new InfiniteProgress();
        Dialog dlg = ip.showInifiniteBlocking();
        dlg.setBackCommand(cancel);

        String url = "https://api.parse.com/1/login";
        request.setUrl(url);
        request.setContentType("application/json");
        request.addRequestHeader("X-Parse-Application-Id", myAppId);
        request.addRequestHeader("X-Parse-REST-API-Key", resApiKey);

        request.setFailSilently(true);//stops user from seeing error message on failure
        request.setPost(false);
        //request.setHttpMethod("PUT");
        request.addArgument("username", bulkSMSUser.getUsername());
        request.addArgument("password", bulkSMSUser.getPassword());
        request.setDuplicateSupported(true);
        request.setDisposeOnCompletion(dlg);

        //NetworkManager manager = NetworkManager.getInstance();
        manager.start();
        manager.addToQueueAndWait(request);


    }

    @Override
    protected void beforeMain(Form f) {

        //Storage.getInstance().clearStorage();
        //Vector<Hashtable> vector = ContactsManager.getAllContacts();
//        if (numberOfCredit == null) {
//            if (!checkCredit.isAlive()) {
//                checkCredit.start();
//            }
//            
//        }
        //;

        Command myGoups = new Command("My Groups") {
            @Override
            public void actionPerformed(ActionEvent evt) {

                showForm("AvaillableGroups", null);
            }
        };

        //f.getMenuBar().addCommand(myGoups);
        f.addCommand(myGoups);

        Command close = new Command("Exit") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                Display.getInstance().exitApplication();
            }
        };

        //f.getMenuBar().addCommand(close);
        f.addCommand(close);

        Command info = new Command("Information") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                showForm("InformationForm", null);
            }
        };

        f.addCommand(info);

        Command checkMyCredit = new Command("Check my Credit") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                if ((numberOfCredit == null) || ("".trim().equals(numberOfCredit))) {
                    checkMyCredit();
                    if ((status == null) || (!"200 OK".equals(status))) {
//                        for (int i = 0; i < userList.size(); i++) {
//                            Hashtable hashtable = userList.elementAt(i);
//                            
//
//                        }
                        Dialog d = new Dialog("Internet connection");
                        TextArea txt = new TextArea();
                        txt.setText("you may not be connected to the internet");
                        txt.setEditable(false);
                        d.addComponent(txt);
                        d.setTimeout(1000);
                        d.show();

                    } else {
                        numberOfCredit = loggedInUser.get("credits").toString();
                        Dialog.show("My Credit", "you have " + numberOfCredit + " units of credit left. On how to purchase more, go to information section", "OK", null);
                    }
                } else {
                    Dialog.show("My Credit", "you have " + numberOfCredit + " units of credit left.On how to purchase more, go to information section", "OK", null);
                }
            }
        };

        f.addCommand(checkMyCredit);

        Command logout = new Command("Logout") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                //kl
                InfiniteProgress ip = new InfiniteProgress();
                //Dialog dlg = ip.showInifiniteBlocking();
                Dialog d = new Dialog();
                d.setDialogUIID("Container");
                d.setLayout(new BorderLayout());
                Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                Label l = new Label("Logging out...");
                l.getStyle().getBgTransparency();
                cnt.addComponent(l);
                cnt.addComponent(ip);
                d.addComponent(BorderLayout.CENTER, cnt);
                d.setTransitionInAnimator(CommonTransitions.createEmpty());
                d.setTransitionOutAnimator(CommonTransitions.createEmpty());
                d.showPacked(BorderLayout.CENTER, false);

                d.setTimeout(3000);
                d.show();
                
                if (Storage.getInstance().exists("BulkSMSUser")) {
                    try {
                        Storage.getInstance().deleteStorageFile("BulkSMSUser");

                        
                    } catch (Exception e) {
                        Dialog.show("Oh dear", e.getMessage(), "OK", null);
                    }
                }
                showForm("SignUp", null);

            }
        };
        f.addCommand(logout);
    }

    @Override
    protected void beforePhoneContacts(Form f) {
//
//        String[] con = ContactsManager.getAllContactsWithNumbers();
//        findPhoneContactsList(f).setModel(new ContactsModel(con));
//        for (int i = 0; i < con.length; i++) {
//            String string = con[i];
//            System.out.println(string);
//
//        }

        f.addCommand(new Command("Home") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                showForm("Main", null);
            }
        });
    }

    @Override
    protected boolean initListModelPhoneContactsList(List cmp) {
        String[] contactsId = ContactsManager.getAllContactsWithNumbers();

        cmp.setModel(new ContactsModel(contactsId));

        return true;
    }

    @Override
    protected void onPhoneContacts_PhoneContactsListAction(Component c, ActionEvent event) {

        List l = (List) c;
        final Hashtable h = (Hashtable) l.getSelectedItem();
        // System.out.println(h.toString());

        Command[] cmds = new Command[2];
        cmds[0] = new Command("yes") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ("family".equals(group)) {
                    if (Storage.getInstance().exists("MyFamilyGroup")) {
                        myFamilyGroup = (Vector<Hashtable>) Storage.getInstance().readObject("MyFamilyGroup");
                        //showForm("GroupDisplay", null);
                    } else {
                        myFamilyGroup = new Vector<Hashtable>();
                    }

                    try {
                        Hashtable h2 = new Hashtable();
                        h2.put("displayName", h.get("displayName").toString());
                        h2.put("phone", h.get("phone").toString());

                        myFamilyGroup.add(h2);
                        try {
                            Storage.getInstance().writeObject("MyFamilyGroup", myFamilyGroup);
                            TextArea txt = new TextArea();
                            txt.setText("contact added");
                            txt.setEditable(false);
                            Dialog d = new Dialog();
                            d.addComponent(txt);
                            d.setTimeout(800);
                            d.show();
                        } catch (Exception e) {
                            Dialog.show("Oh dear!!", "error has occured, trying to add contact '" + e.getMessage() + "'", "OK", null);
                        }
                    } catch (Exception e) {
                        Dialog.show("Oh dear", "contact selected has incomplete details and cannot be added to the group", "OK", null);
                    }


                } else if ("friends".equals(group)) {

                    if (Storage.getInstance().exists("MyFriendsGroup")) {
                        myFriendsGroup = (Vector<Hashtable>) Storage.getInstance().readObject("MyFriendsGroup");
                        //showForm("GroupDisplay", null);
                    } else {
                        myFriendsGroup = new Vector<Hashtable>();
                    }

                    try {
                        Hashtable h2 = new Hashtable();
                        h2.put("displayName", h.get("displayName").toString());
                        h2.put("phone", h.get("phone").toString());

                        myFriendsGroup.add(h2);
                        try {
                            Storage.getInstance().writeObject("MyFriendsGroup", myFriendsGroup);
                            TextArea txt = new TextArea();
                            txt.setText("contact added");
                            txt.setEditable(false);
                            Dialog d = new Dialog();
                            d.addComponent(txt);
                            d.setTimeout(800);
                            d.show();
                        } catch (Exception e) {
                            Dialog.show("Oh dear!!", "error has occured, trying to add contact '" + e.getMessage() + "'", "OK", null);
                        }
                    } catch (Exception e) {
                        Dialog.show("oh dear", "contact selected has incomplete details and cannot be added to the group", "OK", null);
                    }


                } else if ("coworkers".equals(group)) {

                    if (Storage.getInstance().exists("MyCoWorkersGroup")) {
                        myCoWorkersGroup = (Vector<Hashtable>) Storage.getInstance().readObject("MyCoWorkersGroup");
                        //showForm("GroupDisplay", null);
                    } else {
                        myCoWorkersGroup = new Vector<Hashtable>();
                    }

                    try {
                        Hashtable h2 = new Hashtable();
                        h2.put("displayName", h.get("displayName").toString());
                        h2.put("phone", h.get("phone").toString());

                        myCoWorkersGroup.add(h2);
                        try {
                            Storage.getInstance().writeObject("MyCoWorkersGroup", myCoWorkersGroup);
                            TextArea txt = new TextArea();
                            txt.setText("contact added");
                            txt.setEditable(false);
                            Dialog d = new Dialog();
                            d.addComponent(txt);
                            d.setTimeout(800);
                            d.show();
                        } catch (Exception e) {
                            Dialog.show("Oh dear!!", "error has occured, trying to add contact '" + e.getMessage() + "'", "OK", null);
                        }
                    } catch (Exception e) {
                        Dialog.show("oh dear", "contact selected has incomplete details and cannot be added to the group", "OK", null);
                    }


                } else {
                    Dialog.show("Oh dear!!", "what happened?", "OK", null);
                }

            }
        };
        cmds[1] = new Command("no") {
            @Override
            public void actionPerformed(ActionEvent evt) {
            }
        };


        TextArea area = new TextArea();
        //area.setUIID("VKBtooltip");
        area.setEditable(false);
        area.setText("Add " + "'" + h.get("displayName").toString() + "'" + " to the group?");
        Dialog.show("My groups", area, cmds);
    }

    @Override
    protected void onAvaillableGroups_MyFamilyGroupAction(Component c, ActionEvent event) {
        group = "family";
        if (Storage.getInstance().exists("MyFamilyGroup")) {
            myFamilyGroup = (Vector<Hashtable>) Storage.getInstance().readObject("MyFamilyGroup");
            showForm("GroupDisplay", null);
        } else {
            TextArea txt = new TextArea();
            txt.setText("Add members to this group");
            txt.setEditable(false);
            Dialog dlg = new Dialog();
            dlg.addComponent(txt);
            dlg.setTimeout(800);
            dlg.show();
            showForm("PhoneContacts", null);
        }
    }

    @Override
    protected void onAvaillableGroups_MyFriendsGroupAction(Component c, ActionEvent event) {
        group = "friends";
        if (Storage.getInstance().exists("MyFriendsGroup")) {
            myFriendsGroup = (Vector<Hashtable>) Storage.getInstance().readObject("MyFriendsGroup");
            showForm("GroupDisplay", null);
        } else {
            TextArea txt = new TextArea();
            txt.setText("Add members to this group");
            txt.setEditable(false);
            Dialog dlg = new Dialog();
            dlg.addComponent(txt);
            dlg.setTimeout(800);
            dlg.show();
            showForm("PhoneContacts", null);
        }
    }

    @Override
    protected void onAvaillableGroups_MyCoWorkersGroupAction(Component c, ActionEvent event) {
        group = "coworkers";

        if (Storage.getInstance().exists("MyCoWorkersGroup")) {
            myCoWorkersGroup = (Vector<Hashtable>) Storage.getInstance().readObject("MyCoWorkersGroup");
            showForm("GroupDisplay", null);
        } else {
            TextArea txt = new TextArea();
            txt.setText("Add members to this group");
            txt.setEditable(false);
            Dialog dlg = new Dialog();
            dlg.addComponent(txt);
            dlg.setTimeout(800);
            dlg.show();
            showForm("PhoneContacts", null);
        }
    }

    @Override
    protected void beforeGroupDisplay(Form f) {
        List l = findGroupContactsList(f);
        // Label name = findLabel(f);
        if ("family".equals(group)) {
            l.setModel(new DefaultListModel(myFamilyGroup));
            f.setTitle("My Family");
        } else if ("friends".equals(group)) {
            l.setModel(new DefaultListModel(myFriendsGroup));
            f.setTitle("My Friends");
        } else if ("coworkers".equals(group)) {
            l.setModel(new DefaultListModel(myCoWorkersGroup));
            f.setTitle("My Co-workers");
        }

        Command edit = new Command("Add Member") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                showForm("PhoneContacts", null);
            }
        };

        f.addCommand(edit);

        Command send = new Command("Send SMS") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                showForm("SMStoGroup", null);
            }
        };

        f.addCommand(send);

        Command home = new Command("Home") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                showForm("Main", null);
            }
        };

        f.addCommand(home);
    }

    @Override
    protected void beforeSMStoGroup(final Form f) {
        // Label l = findLabel(f);
        if ("family".equals(group)) {
            f.setTitle("My Family");
        } else if ("friends".equals(group)) {
            f.setTitle("My Friends");
        } else if ("coworkers".equals(group)) {
            f.setTitle("My Co-Workers");
        }

        f.addCommand(new Command("Home") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                showForm("Main", null);
            }
        });

        f.addCommand(new Command("Send") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.

                String smsForGroup = findGroupSMSTextArea(f).getText();
                String sender = findGroupSenderTextField(f).getText();

                if (phoneNumbers != null) {
                    phoneNumbers.clear();
                }

                Hashtable h = new Hashtable();
                try {
                    h = (Hashtable) Storage.getInstance().readObject("BulkSMSUser");

                } catch (Exception e) {
                    Dialog.show("Oh dear", "error encountered reading storage media", "OK", null);
                    back();
                }
                if ("".trim().equals(smsForGroup)) {
                    Dialog.show("Empty Field", "you have not entered sms to send yet", "OK", null);
                } else {

                    if ("family".equals(group)) {
                        //myFamilyGroup;
                        for (int i = 0; i < myFamilyGroup.size(); i++) {
                            Hashtable hashtable = myFamilyGroup.elementAt(i);
                            phoneNumbers.add(hashtable.get("phone").toString());

                        }
                    } else if ("friends".equals(group)) {
                        //;
                        for (int i = 0; i < myFriendsGroup.size(); i++) {
                            Hashtable hashtable = myFriendsGroup.elementAt(i);
                            phoneNumbers.add(hashtable.get("phone").toString());

                        }
                    } else if ("coworkers".equals(group)) {
                        //;
                        for (int i = 0; i < myCoWorkersGroup.size(); i++) {
                            Hashtable hashtable = myCoWorkersGroup.elementAt(i);
                            phoneNumbers.add(hashtable.get("phone").toString());

                        }

                    }

                    if (numberOfCredit != null) {
                        // System.out.println("number of " + numberOfCredit);
                        if (phoneNumbers.size() > Integer.valueOf(numberOfCredit)) {
                            Dialog.show("Oh Dear", "Your available number of Credits is '" + numberOfCredit + "'. Please try and purchase more credit before sending", "OK", null);
                        } else {

                            smsToSend = smsForGroup;



                            if ("".trim().equals(sender)) {
                                bulkSMSUser = new BulkSMSUsers(h.get("username").toString(), h.get("password").toString(), h.get("objectId").toString(), h.get("sessionString").toString(), h.get("email").toString());

                                Command[] cmds = new Command[2];
                                cmds[0] = new Command("yes") {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {
                                        thread.start();
                                        TextArea txt = new TextArea();
                                        txt.setText("your message is queued for sending");
                                        txt.setEditable(false);
                                        Dialog dlg = new Dialog();
                                        dlg.addComponent(txt);
                                        dlg.setTimeout(2000);
                                        dlg.show();
                                    }
                                };
                                cmds[1] = new Command("no") {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {
                                        //do Option2
                                    }
                                };

                                //Dialog.show("Confirm", "you haven't entered LOG URL", cmds, BACK_COMMAND_ID, null, 0);

                                TextArea area = new TextArea();
                                //area.setUIID("VKBtooltip");
                                area.setEditable(false);
                                area.setText("You did not provide 'SMS Sender', would you like to send SMS as " + "'" + bulkSMSUser.getUsername() + "' ?");
                                Dialog.show("Please Confirm", area, cmds);
                            } else {
                                bulkSMSUser = new BulkSMSUsers(sender, h.get("password").toString(), h.get("objectId").toString(), h.get("sessionString").toString(), h.get("email").toString());
                                thread.start();

                                TextArea txt = new TextArea();
                                txt.setText("your message is queued for sending");
                                txt.setEditable(false);
                                Dialog dlg = new Dialog();
                                dlg.addComponent(txt);
                                dlg.setTimeout(2000);
                                dlg.show();
                            }

                        }

                    } else {
                        Dialog.show("Oh Dear", "you may be experiencing a network problem because your account details could not be verified, please check your credit again", "OK", null);
                    }
                }

            }
        });
    }

    @Override
    protected void onGroupDisplay_GroupContactsListAction(final Component c, ActionEvent event) {
        final List l = (List) c;
        final Hashtable h = (Hashtable) l.getSelectedItem();
        // System.out.println(h.toString());

        Command[] cmds = new Command[2];
        cmds[0] = new Command("yes") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ("family".equals(group)) {

                    if (Storage.getInstance().exists("MyFamilyGroup")) {
                        myFamilyGroup = (Vector<Hashtable>) Storage.getInstance().readObject("MyFamilyGroup");
                        //showForm("GroupDisplay", null);
                    }

                    for (int i = 0; i < myFamilyGroup.size(); i++) {
                        Hashtable hashtable = myFamilyGroup.elementAt(i);
                        if (h.equals(hashtable)) {
                            myFamilyGroup.removeElementAt(i);
                        }
                    }

                    if (!myFamilyGroup.isEmpty()) {
                        try {
                            Storage.getInstance().writeObject("MyFamilyGroup", myFamilyGroup);
                            TextArea txt = new TextArea();
                            txt.setText("contact removed");
                            txt.setEditable(false);
                            Dialog d = new Dialog();
                            d.addComponent(txt);
                            d.setTimeout(800);
                            d.show();
                            l.setModel(new DefaultListModel(myFamilyGroup));
                        } catch (Exception e) {
                            Dialog.show("Oh dear!!", "error has occured, trying to remove contact '" + e.getMessage() + "'", "OK", null);
                        }
                    } else {
                        try {
                            Storage.getInstance().deleteStorageFile("MyFamilyGroup");
                            TextArea txt = new TextArea();
                            txt.setText("all contacts has been removed");
                            txt.setEditable(false);
                            Dialog d = new Dialog();
                            d.addComponent(txt);
                            d.setTimeout(800);
                            d.show();
                            //back();
                            //c.getComponentForm().showBack();
                            showForm("AvaillableGroups", null);//back();
                            //l.setModel(new DefaultListModel(myFamilyGroup));
                        } catch (Exception e) {
                            Dialog.show("Oh dear!!", "error has occured, trying to remove contact '" + e.getMessage() + "'", "OK", null);
                        }
                        // 
                    }

                } else if ("friends".equals(group)) {


                    if (Storage.getInstance().exists("MyFriendsGroup")) {
                        myFriendsGroup = (Vector<Hashtable>) Storage.getInstance().readObject("MyFriendsGroup");
                        //showForm("GroupDisplay", null);
                    }

                    for (int i = 0; i < myFriendsGroup.size(); i++) {
                        Hashtable hashtable = myFriendsGroup.elementAt(i);
                        if (h.equals(hashtable)) {
                            myFriendsGroup.removeElementAt(i);
                        }
                    }

                    if (!myFriendsGroup.isEmpty()) {
                        try {
                            Storage.getInstance().writeObject("MyFriendsGroup", myFriendsGroup);
                            TextArea txt = new TextArea();
                            txt.setText("contact removed");
                            txt.setEditable(false);
                            Dialog d = new Dialog();
                            d.addComponent(txt);
                            d.setTimeout(800);
                            d.show();
                            l.setModel(new DefaultListModel(myFriendsGroup));
                        } catch (Exception e) {
                            Dialog.show("Oh dear!!", "error has occured, trying to remove contact '" + e.getMessage() + "'", "OK", null);
                        }

                    } else {
                        try {
                            Storage.getInstance().deleteStorageFile("MyFriendsGroup");
                            TextArea txt = new TextArea();
                            txt.setText("all contacts have been removed");
                            txt.setEditable(false);
                            Dialog d = new Dialog();
                            d.addComponent(txt);
                            //d.addComponent(new Label(""));
                            d.setTimeout(800);
                            d.show();
                            showForm("AvaillableGroups", null);//back();
                            //l.setModel(new DefaultListModel(myFriendsGroup));
                        } catch (Exception e) {
                            Dialog.show("Oh dear!!", "error has occured, trying to remove contact '" + e.getMessage() + "'", "OK", null);
                        }

                    }

                } else if ("coworkers".equals(group)) {

                    if (Storage.getInstance().exists("MyCoWorkersGroup")) {
                        myCoWorkersGroup = (Vector<Hashtable>) Storage.getInstance().readObject("MyCoWorkersGroup");
                        //showForm("GroupDisplay", null);
                    }

                    for (int i = 0; i < myCoWorkersGroup.size(); i++) {
                        Hashtable hashtable = myCoWorkersGroup.elementAt(i);
                        if (h.equals(hashtable)) {
                            myCoWorkersGroup.removeElementAt(i);
                        }
                    }
                    if (!myCoWorkersGroup.isEmpty()) {
                        try {
                            Storage.getInstance().writeObject("MyCoWorkersGroup", myCoWorkersGroup);
                            TextArea txt = new TextArea();
                            txt.setText("contact removed");
                            txt.setEditable(false);
                            Dialog d = new Dialog();
                            d.addComponent(txt);
                            d.setTimeout(800);
                            d.show();
                            l.setModel(new DefaultListModel(myCoWorkersGroup));
                        } catch (Exception e) {
                            Dialog.show("Oh dear!!", "error has occured, trying to remove contact '" + e.getMessage() + "'", "OK", null);
                        }
                    } else {
                        try {
                            Storage.getInstance().deleteStorageFile("MyCoWorkersGroup");
                            TextArea txt = new TextArea();
                            txt.setText("all contacts removed");
                            txt.setEditable(false);
                            Dialog d = new Dialog();
                            d.addComponent(txt);
                            d.setTimeout(800);
                            d.show();
                            showForm("AvaillableGroups", null);//back();
                            // l.setModel(new DefaultListModel(myCoWorkersGroup));

                            //back();
                        } catch (Exception e) {
                            Dialog.show("Oh dear!!", "error has occured, trying to remove contact '" + e.getMessage() + "'", "OK", null);
                        }
                    }


                } else {
                    Dialog.show("Oh dear!!", "what happened?", "OK", null);
                }

            }
        };
        cmds[1] = new Command("no") {
            @Override
            public void actionPerformed(ActionEvent evt) {
            }
        };


        TextArea area = new TextArea();
        //area.setUIID("VKBtooltip");
        area.setEditable(false);
        area.setText("Remove " + "'" + h.get("displayName").toString() + "'" + " from the group?");
        Dialog.show("My groups", area, cmds);

        // c.getComponentForm().revalidate();
    }

    @Override
    protected boolean allowBackTo(String formName) {
        //return super.allowBackTo(formName); //To change body of generated methods, choose Tools | Templates.
        if ("SplashScreen".equals(formName)) {
            return false;
        }
        return true;
    }

    @Override
    protected boolean processBackground(Form f) {
        super.processBackground(f); //To change body of generated methods, choose Tools | Templates.
        //super.processBackground(f);
        if ("SplashScreen".equals(f.getName())) {

            if (!Storage.getInstance().exists("BulkSMSUser")) {

                showForm("SignUp", null);
                return false;

            } else {
                Hashtable h = (Hashtable) Storage.getInstance().readObject("BulkSMSUser");
                bulkSMSUser = new BulkSMSUsers(h.get("username").toString(), h.get("password").toString(), h.get("objectId").toString(), h.get("sessionString").toString(), h.get("email").toString());

                checkCredit.start();
                //return true;
            }
            //return true;//loadDataFromStorage();
        }
        return true;
    }

    public void noInternet() {
        TextArea txt = new TextArea();
        txt.setText("you may not be connected to the internet");
        txt.setEditable(false);
        Dialog dlg = new Dialog("Oh Dear");
        dlg.addComponent(txt);
        dlg.setTimeout(3000);
        dlg.show();
    }

    @Override
    protected void onSignUp_RegisterUserButtonAction(Component c, ActionEvent event) {

        String username = findUsernameTextField(c.getComponentForm()).getText();
        String pasword = findPasswordTextField(c.getComponentForm()).getText();
        String confirmPassword = findConfirmPasswordTextField(c.getComponentForm()).getText();
        String email = findEmailTextField(c.getComponentForm()).getText();

        if (("".trim().equals(username)) || ("".trim()).equals(pasword) || ("".trim().equals(email))) {
            TextArea txt = new TextArea();
            txt.setText("all fields are required");
            txt.setEditable(false);
            Dialog dlg = new Dialog("Empty Field");
            dlg.addComponent(txt);
            dlg.setTimeout(2000);
            dlg.show();
        } else {
            if ((email.indexOf("@") < 0) || (email.indexOf(".", email.indexOf("@")) < 0)) {
                Dialog.show("Email", "invalid email format", "OK", null);
            } else {
                if (encodePWD(pasword).equals(encodePWD(confirmPassword))) {

                    searchFor(username.toLowerCase(), email);
                    if ((status != null) && ("200 OK".equals(status))) {
                        //System.out.println(userList.size());
                        status = "";
                        if (userList.size() > 0) {
                            Dialog.show("Oh Dear", "'" + username + "' " + "or '" + email + "' already exists. if you have registered before, try logging in.", "OK", null);
//                        TextArea txt = new TextArea();
//                        txt.setText("'" + username + "' is already in use by another user. please choose another username");
//                        txt.setEditable(false);
//                        Dialog dlg = new Dialog("Oh Dear");
//                        dlg.addComponent(txt);
//                        dlg.setTimeout(3000);
//                        dlg.show();
                        } else {
                            registerAppUser(username.toLowerCase(), encodePWD(pasword), email);

                            if ((status != null) && ("201 Created".equals(status))) {


                                Hashtable h = new Hashtable();
                                h.put("username", username);
                                h.put("password", encodePWD(pasword));
                                h.put("objectId", userInfo.get("objectId").toString());
                                h.put("sessionString", userInfo.get("sessionString").toString());
                                h.put("email", email);

//                            for (int i = 0; i < userInfo.size(); i++) {
//                                Hashtable hashtable = userInfo.elementAt(i);

                                //}

                                try {
                                    Storage.getInstance().writeObject("BulkSMSUser", h);
                                } catch (Exception e) {
                                    Dialog.show("oh dear", "error encountered trying to read the storage media '" + e.getMessage() + "'", "OK", null);
                                }

                                Dialog.show("Welcome", "'" + username + "' welcome to the Bulk SMS service. You have '3' free bulk SMS Credits. To purchase Credit, check Info section", "OK", null);
//                            TextArea txt = new TextArea();
//                            txt.setText("'" + username + "' welcome to the Bulk SMS service. You have '3' free bulk SMS Credits. To purchase Credit, check Info section");
//                            txt.setEditable(false);
//                            Dialog dlg = new Dialog("Welcome");
//                            dlg.addComponent(txt);
//                            dlg.setTimeout(3000);
//                            dlg.show();
                                numberOfCredit = "3";
                                showForm("Main", null);
                            } else {
                                noInternet();
                            }
                        }
                    } else {
                        noInternet();
                    }
                } else {
                    TextArea txt = new TextArea();
                    txt.setText("the two passwords do not match");
                    txt.setEditable(false);
                    Dialog dlg = new Dialog("Password");
                    dlg.addComponent(txt);
                    dlg.setTimeout(3000);
                    dlg.show();
                }
            }

        }
    }
    Thread thread = new Thread(new Runnable() {
        final Vector<String> smsSent = new Vector<String>();

        public void run() {

            for (int i = 0; i < phoneNumbers.size(); i++) {
                String string = phoneNumbers.elementAt(i);
                if (string.startsWith("0")) {
                    string = "234" + string.substring(1);
                } else if (string.startsWith("+")) {
                    string = string.substring(1);
                }

                final String number = string;

                final ConnectionRequest request = new ConnectionRequest() {
                    // **************** Get the status of the connection        
                    @Override
                    protected void readHeaders(Object connection) throws IOException {
                        //status = getHeader(connection, "status");
                        // System.out.println("The status of the connection: " + status);
                    }
                    //*****************

                    @Override
                    protected void readResponse(InputStream input) throws IOException {
                        status = String.valueOf(getResponseCode());
                        JSONParser p = new JSONParser();
                        InputStreamReader inp = new InputStreamReader(input);
                        Hashtable h = p.parse(inp);

                        //final Vector v = (Vector) h.get("results");
                        //System.out.println("--------------------------------------------------");
                        //contactList.clear();
                        smsResponse = (String) h.get("result");
                        //String txt = new String();

                        // System.out.println(smsResponse);
                        //System.out.println(smsResponse.substring(0, 27));

                    }

                    @Override
                    protected void postResponse() {
                        // super.postResponse(); //To change body of generated methods, choose Tools | Templates.
                        if ("200".equals(status)) {
                            smsSent.add(number);

                        }

                    }
                };

                String url = "http://107.20.195.151/mcast_ws_v2/index.php?user=godwin_agada&password=gochech123&from=" + Util.encodeUrl(bulkSMSUser.getUsername()) + "&to=" + number + "&message=" + Util.encodeUrl(smsToSend) + "&type=json";
                request.setUrl(url);
                request.setFailSilently(true);//stops user from seeing error message on failure
                request.setPost(false);
                request.setDuplicateSupported(true);
                //request.setDisposeOnCompletion(dlg);

                NetworkManager manager = NetworkManager.getInstance();
                manager.start();
                manager.addToQueueAndWait(request);
            }



            if (smsSent.size() > 0) {
                numberOfCredit = String.valueOf((Integer.valueOf(numberOfCredit)) - (smsSent.size()));
//                int num;
//                num = (Integer.valueOf(numberOfCredit) - );
                Hashtable h = new Hashtable();
                h.put("credits", numberOfCredit);

                final String json = Result.fromContent(h).toString();

                final ConnectionRequest request = new ConnectionRequest() {
                    @Override
                    protected void buildRequestBody(OutputStream os) throws IOException {
                        os.write(json.getBytes("UTF-8"));
                    }

                    // **************** Get the status of the connection        
                    @Override
                    protected void readHeaders(Object connection) throws IOException {
                        status = getHeader(connection, "status");
//                System.out.println("The status of the connection: " + status);
//                System.out.println(connection.toString());
                    }
                    //*****************

                    @Override
                    protected void readResponse(InputStream input) throws IOException {
                        //status = String.valueOf(getResposeCode());

                        // offlineTellerResponse = Result.fromContent(input, Result.XML);
                        //Header(connection, "status");
                        JSONParser p = new JSONParser();
                        InputStreamReader inp = new InputStreamReader(input);
                        Hashtable h = p.parse(inp);

//                System.out.println("--------------------After Saving the Contact------------------------------");
//
//                System.out.println(h.toString());

                    }
                };



                final NetworkManager manager = NetworkManager.getInstance();

                String url = "https://api.parse.com/1/users/" + bulkSMSUser.getObjectId();
                request.setUrl(url);
                request.setContentType("application/json");
                request.addRequestHeader("X-Parse-Application-Id", myAppId);
                request.addRequestHeader("X-Parse-REST-API-Key", resApiKey);
                request.addRequestHeader("X-Parse-Session-Token", bulkSMSUser.getSessionToken());
                request.setFailSilently(true);//stops user from seeing error message on failure
                request.setPost(true);
                request.setHttpMethod("PUT");
                request.setDuplicateSupported(true);

                manager.start();
                manager.addToQueueAndWait(request);
            }

        }
    });

    @Override
    protected void onMain_SendSMSButtonAction(Component c, ActionEvent event) {

        Hashtable h = new Hashtable();
        try {
            h = (Hashtable) Storage.getInstance().readObject("BulkSMSUser");

        } catch (Exception e) {
            Dialog.show("Oh dear", "error encountered reading storage media", "OK", null);
        }

        String phoneNumber = findNumbersToSendSMSTextArea(c.getComponentForm()).getText();
        String sender = findSmsSenderTextField(c.getComponentForm()).getText();
        String sms = findSmsToSendTextArea(c.getComponentForm()).getText();


        if (("".trim().equals(phoneNumber)) || ("".trim().equals(sms))) {
            Dialog.show("Empty Field", "please check that you have entered all required fields", "OK", null);
        } else {
            phoneNumbers = StringUtil.tokenizeString(phoneNumber, ",");
            if (numberOfCredit != null) {
                // System.out.println("number of " + numberOfCredit);
                if (phoneNumbers.size() > Integer.valueOf(numberOfCredit)) {
                    Dialog.show("Oh Dear", "Your available number of Credits is '" + numberOfCredit + "'. Please try and purchase more credit before sending", "OK", null);
                } else {

                    smsToSend = sms;



                    if ("".trim().equals(sender)) {
                        bulkSMSUser = new BulkSMSUsers(h.get("username").toString(), h.get("password").toString(), h.get("objectId").toString(), h.get("sessionString").toString(), h.get("email").toString());

                        Command[] cmds = new Command[2];
                        cmds[0] = new Command("yes") {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                thread.start();
                                TextArea txt = new TextArea();
                                txt.setText("your message is queued for sending");
                                txt.setEditable(false);
                                Dialog dlg = new Dialog();
                                dlg.addComponent(txt);
                                dlg.setTimeout(2000);
                                dlg.show();
                            }
                        };
                        cmds[1] = new Command("no") {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                //do Option2
                            }
                        };

                        //Dialog.show("Confirm", "you haven't entered LOG URL", cmds, BACK_COMMAND_ID, null, 0);

                        TextArea area = new TextArea();
                        //area.setUIID("VKBtooltip");
                        area.setEditable(false);
                        area.setText("You did not provide 'SMS Sender', would you like to send SMS as " + "'" + bulkSMSUser.getUsername() + "' ?");
                        Dialog.show("Please Confirm", area, cmds);
                    } else {
                        bulkSMSUser = new BulkSMSUsers(sender, h.get("password").toString(), h.get("objectId").toString(), h.get("sessionString").toString(), h.get("email").toString());
                        thread.start();

                        TextArea txt = new TextArea();
                        txt.setText("your message is queued for sending");
                        txt.setEditable(false);
                        Dialog dlg = new Dialog();
                        dlg.addComponent(txt);
                        dlg.setTimeout(2000);
                        dlg.show();
                    }

                }

            } else {
                Dialog.show("Oh Dear", "you may be experiencing a network problem because your account details could not be verified, please check your credit again", "OK", null);
            }


        }

        //numbers = 
//
//        for (int i = 0; i < phoneNumbers.size(); i++) {
//            String string = phoneNumbers.elementAt(i);
//            System.out.println(i + " " + string);
//        }

    }

    @Override
    protected void beforeSplashScreen(Form f) {
        //Storage.getInstance().clearStorage();
    }

    @Override
    protected void beforeInformationForm(Form f) {

        TextArea area = findInfoTextArea(f);
        area.setText("Payment Plans \n"
                + "Bronze 	1 - 10,000 	N2.50K \n"
                + "Silver 	10,001 - 50,000 	N2.00K \n"
                + "Gold 	50,001 - 500,000 	N1.70K \n"
                + "Platinum 	500,001 - 1,000,000 	N1.40K \n"
                + "Custom 	1,000,001 and Above 	PLEASE CALL");

        TextArea text = findInfoNumbersTextArea(f);

        text.setText("Make payments to the following account number : \n"
                + "Agada Godwin C. \n"
                + "Keystone Savings Acct \n"
                + "6005446430 \n"
                + "\n After payment, send a text message in this format \n"
                + "'<username>' payed <amount> with slip number <number>");

        TextArea firstArea = findFirstTextArea(f);

        firstArea.setText("Once payment is made, a text message should be sent immediatelly so that you will be credited.\n"
                + "Number to send text to is  08034453370 or 08094928523");
    }

    @Override
    protected void onSMStoGroup_SendSMStoGroupButtonAction(Component c, ActionEvent event) {

        String smsForGroup = findGroupSMSTextArea(c.getComponentForm()).getText();
        String sender = findGroupSenderTextField(c.getComponentForm()).getText();

        if (phoneNumbers == null) {
            phoneNumbers = new Vector<String>();
        } else {
            phoneNumbers.clear();
        }

        Hashtable h = new Hashtable();
        try {
            h = (Hashtable) Storage.getInstance().readObject("BulkSMSUser");

        } catch (Exception e) {
            Dialog.show("Oh dear", "error encountered reading storage media", "OK", null);
            back();
        }
        if ("".trim().equals(smsForGroup)) {
            Dialog.show("Empty Field", "you have not entered sms to send yet", "OK", null);
        } else {

            if ("family".equals(group)) {
                //myFamilyGroup;
                for (int i = 0; i < myFamilyGroup.size(); i++) {
                    Hashtable hashtable = myFamilyGroup.elementAt(i);
                    phoneNumbers.add(hashtable.get("phone").toString());

                }
            } else if ("friends".equals(group)) {
                //;
                for (int i = 0; i < myFriendsGroup.size(); i++) {
                    Hashtable hashtable = myFriendsGroup.elementAt(i);
                    phoneNumbers.add(hashtable.get("phone").toString());

                }
            } else if ("coworkers".equals(group)) {
                //;
                for (int i = 0; i < myCoWorkersGroup.size(); i++) {
                    Hashtable hashtable = myCoWorkersGroup.elementAt(i);
                    phoneNumbers.add(hashtable.get("phone").toString());

                }

            }

            if (numberOfCredit != null) {
                // System.out.println("number of " + numberOfCredit);
                if (phoneNumbers.size() > Integer.valueOf(numberOfCredit)) {
                    Dialog.show("Oh Dear", "Your available number of Credits is '" + numberOfCredit + "'. Please try and purchase more credit before sending", "OK", null);
                } else {

                    smsToSend = smsForGroup;



                    if ("".trim().equals(sender)) {
                        bulkSMSUser = new BulkSMSUsers(h.get("username").toString(), h.get("password").toString(), h.get("objectId").toString(), h.get("sessionString").toString(), h.get("email").toString());

                        Command[] cmds = new Command[2];
                        cmds[0] = new Command("yes") {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                thread.start();
                                TextArea txt = new TextArea();
                                txt.setText("your message is queued for sending");
                                txt.setEditable(false);
                                Dialog dlg = new Dialog();
                                dlg.addComponent(txt);
                                dlg.setTimeout(2000);
                                dlg.show();
                            }
                        };
                        cmds[1] = new Command("no") {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                //do Option2
                            }
                        };

                        //Dialog.show("Confirm", "you haven't entered LOG URL", cmds, BACK_COMMAND_ID, null, 0);

                        TextArea area = new TextArea();
                        //area.setUIID("VKBtooltip");
                        area.setEditable(false);
                        area.setText("You did not provide 'SMS Sender', would you like to send SMS as " + "'" + bulkSMSUser.getUsername() + "' ?");
                        Dialog.show("Please Confirm", area, cmds);
                    } else {
                        bulkSMSUser = new BulkSMSUsers(sender, h.get("password").toString(), h.get("objectId").toString(), h.get("sessionString").toString(), h.get("email").toString());
                        thread.start();

                        TextArea txt = new TextArea();
                        txt.setText("your message is queued for sending");
                        txt.setEditable(false);
                        Dialog dlg = new Dialog();
                        dlg.addComponent(txt);
                        dlg.setTimeout(2000);
                        dlg.show();
                    }

                }

            } else {
                Dialog.show("Oh Dear", "you may be experiencing a network problem because your account details could not be verified, please check your credit again", "OK", null);
            }
        }


    }

    @Override
    protected void beforeSignUp(Form f) {
        f.addCommand(new Command("Close") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                Display.getInstance().exitApplication();
            }
        });
    }

    @Override
    protected void onSignUp_SignInButtonAction(Component c, ActionEvent event) {

        showForm("LoginUser", null);
    }

    @Override
    protected void onLoginUser_LoginButtonAction(Component c, ActionEvent event) {
        String username = findSmsUsername(c.getComponentForm()).getText();
        String pwd = findSmsPassword(c.getComponentForm()).getText();

        userLogin(username.toLowerCase(), encodePWD(pwd));
        if ((status == null) || (!"200 OK".equals(status))) {
            // ((Dialog) Display.getInstance().getCurrent()).dispose();
            Dialog.show("user login", "please check your login details", "OK", null);
            //noInternet();
        } else {
            Dialog.show("user login", "welcome back '" + username + "', we missed you", "OK", null);
            loggedInUser.put("password", encodePWD(pwd));
            try {
                Storage.getInstance().writeObject("BulkSMSUser", loggedInUser);
                ((Dialog) Display.getInstance().getCurrent()).dispose();
                showForm("Main", null);

            } catch (Exception e) {
                Dialog.show("oh dear", "error encountered trying to read the storage media '" + e.getMessage() + "'", "OK", null);
            }
        }
    }

    @Override
    protected void onLoginUser_CancelAction(Component c, ActionEvent event) {
        ((Dialog) Display.getInstance().getCurrent()).dispose();
    }

    @Override
    protected void onResetMyPassword_ResetDePasswordAction(Component c, ActionEvent event) {
        String email = findEmailToReset(c.getComponentForm()).getText();

        if ((email.indexOf("@") < 0) || (email.indexOf(".", email.indexOf("@")) < 0)) {
            Dialog.show("Email", "invalid email format", "OK", null);
        } else {
            pleaseResetMyPassword(email);
            if (status != null) {
                if ("200 OK".equals(status)) {
                    ((Dialog) Display.getInstance().getCurrent()).dispose();
                    Dialog.show("Password reset", "an email has been sent to '" + email + "' . check your mail to provide the new password, and try login again", "OK", null);
                } else {
                    Dialog.show("Oh Dear", "your password reset was not successful. did you provide the correct email address?", "OK", null);
                }
            } else {
                Dialog.show("Oh Dear", "your password reset was not successful. did you provide the correct email address?", "OK", null);
                //noInternet();
            }
        }
    }

    @Override
    protected void onResetMyPassword_CancelAction(Component c, ActionEvent event) {
        ((Dialog) Display.getInstance().getCurrent()).dispose();
    }

    @Override
    protected void onLoginUser_ResetPasswordButtonAction(Component c, ActionEvent event) {
        ((Dialog) Display.getInstance().getCurrent()).dispose();
        showForm("ResetMyPassword", null);
    }

    @Override
    protected void beforeAvaillableGroups(Form f) {
        f.setBackCommand(new Command("Home") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                showForm("Main", null);
            }
        });
    }

    @Override
    protected void onMain_AddNumbersButtonAction(Component c, ActionEvent event) {

    
    }
}
