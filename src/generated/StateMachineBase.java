/**
 * This class contains generated code from the Codename One Designer, DO NOT MODIFY!
 * This class is designed for subclassing that way the code generator can overwrite it
 * anytime without erasing your changes which should exist in a subclass!
 * For details about this file and how it works please read this blog post:
 * http://codenameone.blogspot.com/2010/10/ui-builder-class-how-to-actually-use.html
*/
package generated;

import com.codename1.ui.*;
import com.codename1.ui.util.*;
import com.codename1.ui.plaf.*;
import java.util.Hashtable;
import com.codename1.ui.events.*;

public abstract class StateMachineBase extends UIBuilder {
    private Container aboutToShowThisContainer;
    /**
     * this method should be used to initialize variables instead of
     * the constructor/class scope to avoid race conditions
     */
    /**
    * @deprecated use the version that accepts a resource as an argument instead
    
**/
    protected void initVars() {}

    protected void initVars(Resources res) {}

    public StateMachineBase(Resources res, String resPath, boolean loadTheme) {
        startApp(res, resPath, loadTheme);
    }

    public Container startApp(Resources res, String resPath, boolean loadTheme) {
        initVars();
        UIBuilder.registerCustomComponent("Button", com.codename1.ui.Button.class);
        UIBuilder.registerCustomComponent("Form", com.codename1.ui.Form.class);
        UIBuilder.registerCustomComponent("InfiniteProgress", com.codename1.components.InfiniteProgress.class);
        UIBuilder.registerCustomComponent("Label", com.codename1.ui.Label.class);
        UIBuilder.registerCustomComponent("Dialog", com.codename1.ui.Dialog.class);
        UIBuilder.registerCustomComponent("TextArea", com.codename1.ui.TextArea.class);
        UIBuilder.registerCustomComponent("List", com.codename1.ui.List.class);
        UIBuilder.registerCustomComponent("TextField", com.codename1.ui.TextField.class);
        UIBuilder.registerCustomComponent("Container", com.codename1.ui.Container.class);
        if(loadTheme) {
            if(res == null) {
                try {
                    if(resPath.endsWith(".res")) {
                        res = Resources.open(resPath);
                        System.out.println("Warning: you should construct the state machine without the .res extension to allow theme overlays");
                    } else {
                        res = Resources.openLayered(resPath);
                    }
                } catch(java.io.IOException err) { err.printStackTrace(); }
            }
            initTheme(res);
        }
        if(res != null) {
            setResourceFilePath(resPath);
            setResourceFile(res);
            initVars(res);
            return showForm(getFirstFormName(), null);
        } else {
            Form f = (Form)createContainer(resPath, getFirstFormName());
            initVars(fetchResourceFile());
            beforeShow(f);
            f.show();
            postShow(f);
            return f;
        }
    }

    protected String getFirstFormName() {
        return "SplashScreen";
    }

    public Container createWidget(Resources res, String resPath, boolean loadTheme) {
        initVars();
        UIBuilder.registerCustomComponent("Button", com.codename1.ui.Button.class);
        UIBuilder.registerCustomComponent("Form", com.codename1.ui.Form.class);
        UIBuilder.registerCustomComponent("InfiniteProgress", com.codename1.components.InfiniteProgress.class);
        UIBuilder.registerCustomComponent("Label", com.codename1.ui.Label.class);
        UIBuilder.registerCustomComponent("Dialog", com.codename1.ui.Dialog.class);
        UIBuilder.registerCustomComponent("TextArea", com.codename1.ui.TextArea.class);
        UIBuilder.registerCustomComponent("List", com.codename1.ui.List.class);
        UIBuilder.registerCustomComponent("TextField", com.codename1.ui.TextField.class);
        UIBuilder.registerCustomComponent("Container", com.codename1.ui.Container.class);
        if(loadTheme) {
            if(res == null) {
                try {
                    res = Resources.openLayered(resPath);
                } catch(java.io.IOException err) { err.printStackTrace(); }
            }
            initTheme(res);
        }
        return createContainer(resPath, "SplashScreen");
    }

    protected void initTheme(Resources res) {
            String[] themes = res.getThemeResourceNames();
            if(themes != null && themes.length > 0) {
                UIManager.getInstance().setThemeProps(res.getTheme(themes[0]));
            }
    }

    public StateMachineBase() {
    }

    public StateMachineBase(String resPath) {
        this(null, resPath, true);
    }

    public StateMachineBase(Resources res) {
        this(res, null, true);
    }

    public StateMachineBase(String resPath, boolean loadTheme) {
        this(null, resPath, loadTheme);
    }

    public StateMachineBase(Resources res, boolean loadTheme) {
        this(res, null, loadTheme);
    }

    public com.codename1.ui.Container findContainer4(Component root) {
        return (com.codename1.ui.Container)findByName("Container4", root);
    }

    public com.codename1.ui.Container findContainer4() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container4", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container4", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findSmsUsername(Component root) {
        return (com.codename1.ui.TextField)findByName("smsUsername", root);
    }

    public com.codename1.ui.TextField findSmsUsername() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("smsUsername", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("smsUsername", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer3(Component root) {
        return (com.codename1.ui.Container)findByName("Container3", root);
    }

    public com.codename1.ui.Container findContainer3() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container3", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container3", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findSignInButton(Component root) {
        return (com.codename1.ui.Button)findByName("signInButton", root);
    }

    public com.codename1.ui.Button findSignInButton() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("signInButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("signInButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findPhone(Component root) {
        return (com.codename1.ui.Label)findByName("phone", root);
    }

    public com.codename1.ui.Label findPhone() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("phone", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("phone", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer2(Component root) {
        return (com.codename1.ui.Container)findByName("Container2", root);
    }

    public com.codename1.ui.Container findContainer2() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container2", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container2", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer1(Component root) {
        return (com.codename1.ui.Container)findByName("Container1", root);
    }

    public com.codename1.ui.Container findContainer1() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container1", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container1", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer6(Component root) {
        return (com.codename1.ui.Container)findByName("Container6", root);
    }

    public com.codename1.ui.Container findContainer6() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container6", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container6", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer5(Component root) {
        return (com.codename1.ui.Container)findByName("Container5", root);
    }

    public com.codename1.ui.Container findContainer5() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container5", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container5", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findSendSMStoGroupButton(Component root) {
        return (com.codename1.ui.Button)findByName("sendSMStoGroupButton", root);
    }

    public com.codename1.ui.Button findSendSMStoGroupButton() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("sendSMStoGroupButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("sendSMStoGroupButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findCancel(Component root) {
        return (com.codename1.ui.Button)findByName("cancel", root);
    }

    public com.codename1.ui.Button findCancel() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("cancel", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("cancel", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findPasswordTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("passwordTextField", root);
    }

    public com.codename1.ui.TextField findPasswordTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("passwordTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("passwordTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContactsRenderer(Component root) {
        return (com.codename1.ui.Container)findByName("ContactsRenderer", root);
    }

    public com.codename1.ui.Container findContactsRenderer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("ContactsRenderer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("ContactsRenderer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findLabel1(Component root) {
        return (com.codename1.ui.Label)findByName("Label1", root);
    }

    public com.codename1.ui.Label findLabel1() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("Label1", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("Label1", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findLabel3(Component root) {
        return (com.codename1.ui.Label)findByName("Label3", root);
    }

    public com.codename1.ui.Label findLabel3() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("Label3", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("Label3", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findLabel2(Component root) {
        return (com.codename1.ui.Label)findByName("Label2", root);
    }

    public com.codename1.ui.Label findLabel2() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("Label2", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("Label2", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextArea findFirstTextArea(Component root) {
        return (com.codename1.ui.TextArea)findByName("firstTextArea", root);
    }

    public com.codename1.ui.TextArea findFirstTextArea() {
        com.codename1.ui.TextArea cmp = (com.codename1.ui.TextArea)findByName("firstTextArea", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextArea)findByName("firstTextArea", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.List findGroupContactsList(Component root) {
        return (com.codename1.ui.List)findByName("groupContactsList", root);
    }

    public com.codename1.ui.List findGroupContactsList() {
        com.codename1.ui.List cmp = (com.codename1.ui.List)findByName("groupContactsList", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.List)findByName("groupContactsList", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextArea findGroupSMSTextArea(Component root) {
        return (com.codename1.ui.TextArea)findByName("groupSMSTextArea", root);
    }

    public com.codename1.ui.TextArea findGroupSMSTextArea() {
        com.codename1.ui.TextArea cmp = (com.codename1.ui.TextArea)findByName("groupSMSTextArea", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextArea)findByName("groupSMSTextArea", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findEmailToReset(Component root) {
        return (com.codename1.ui.TextField)findByName("emailToReset", root);
    }

    public com.codename1.ui.TextField findEmailToReset() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("emailToReset", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("emailToReset", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findConfirmPasswordTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("confirmPasswordTextField", root);
    }

    public com.codename1.ui.TextField findConfirmPasswordTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("confirmPasswordTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("confirmPasswordTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findResetDePassword(Component root) {
        return (com.codename1.ui.Button)findByName("resetDePassword", root);
    }

    public com.codename1.ui.Button findResetDePassword() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("resetDePassword", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("resetDePassword", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextArea findNumbersToSendSMSTextArea(Component root) {
        return (com.codename1.ui.TextArea)findByName("numbersToSendSMSTextArea", root);
    }

    public com.codename1.ui.TextArea findNumbersToSendSMSTextArea() {
        com.codename1.ui.TextArea cmp = (com.codename1.ui.TextArea)findByName("numbersToSendSMSTextArea", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextArea)findByName("numbersToSendSMSTextArea", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findLoginButton(Component root) {
        return (com.codename1.ui.Button)findByName("loginButton", root);
    }

    public com.codename1.ui.Button findLoginButton() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("loginButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("loginButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextArea findInfoTextArea(Component root) {
        return (com.codename1.ui.TextArea)findByName("infoTextArea", root);
    }

    public com.codename1.ui.TextArea findInfoTextArea() {
        com.codename1.ui.TextArea cmp = (com.codename1.ui.TextArea)findByName("infoTextArea", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextArea)findByName("infoTextArea", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findAddNumbersButton(Component root) {
        return (com.codename1.ui.Button)findByName("addNumbersButton", root);
    }

    public com.codename1.ui.Button findAddNumbersButton() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("addNumbersButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("addNumbersButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findUsernameTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("usernameTextField", root);
    }

    public com.codename1.ui.TextField findUsernameTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("usernameTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("usernameTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findSendSMSButton(Component root) {
        return (com.codename1.ui.Button)findByName("sendSMSButton", root);
    }

    public com.codename1.ui.Button findSendSMSButton() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("sendSMSButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("sendSMSButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.List findPhoneContactsList(Component root) {
        return (com.codename1.ui.List)findByName("phoneContactsList", root);
    }

    public com.codename1.ui.List findPhoneContactsList() {
        com.codename1.ui.List cmp = (com.codename1.ui.List)findByName("phoneContactsList", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.List)findByName("phoneContactsList", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer(Component root) {
        return (com.codename1.ui.Container)findByName("Container", root);
    }

    public com.codename1.ui.Container findContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findGroupSenderTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("groupSenderTextField", root);
    }

    public com.codename1.ui.TextField findGroupSenderTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("groupSenderTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("groupSenderTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findEmailTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("emailTextField", root);
    }

    public com.codename1.ui.TextField findEmailTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("emailTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("emailTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findMyFriendsGroup(Component root) {
        return (com.codename1.ui.Button)findByName("myFriendsGroup", root);
    }

    public com.codename1.ui.Button findMyFriendsGroup() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("myFriendsGroup", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("myFriendsGroup", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findMyCoWorkersGroup(Component root) {
        return (com.codename1.ui.Button)findByName("myCoWorkersGroup", root);
    }

    public com.codename1.ui.Button findMyCoWorkersGroup() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("myCoWorkersGroup", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("myCoWorkersGroup", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.InfiniteProgress findInfiniteProgress(Component root) {
        return (com.codename1.components.InfiniteProgress)findByName("InfiniteProgress", root);
    }

    public com.codename1.components.InfiniteProgress findInfiniteProgress() {
        com.codename1.components.InfiniteProgress cmp = (com.codename1.components.InfiniteProgress)findByName("InfiniteProgress", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.InfiniteProgress)findByName("InfiniteProgress", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findMyFamilyGroup(Component root) {
        return (com.codename1.ui.Button)findByName("myFamilyGroup", root);
    }

    public com.codename1.ui.Button findMyFamilyGroup() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("myFamilyGroup", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("myFamilyGroup", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextArea findInfoNumbersTextArea(Component root) {
        return (com.codename1.ui.TextArea)findByName("infoNumbersTextArea", root);
    }

    public com.codename1.ui.TextArea findInfoNumbersTextArea() {
        com.codename1.ui.TextArea cmp = (com.codename1.ui.TextArea)findByName("infoNumbersTextArea", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextArea)findByName("infoNumbersTextArea", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findResetPasswordButton(Component root) {
        return (com.codename1.ui.Button)findByName("resetPasswordButton", root);
    }

    public com.codename1.ui.Button findResetPasswordButton() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("resetPasswordButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("resetPasswordButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextArea findSmsToSendTextArea(Component root) {
        return (com.codename1.ui.TextArea)findByName("smsToSendTextArea", root);
    }

    public com.codename1.ui.TextArea findSmsToSendTextArea() {
        com.codename1.ui.TextArea cmp = (com.codename1.ui.TextArea)findByName("smsToSendTextArea", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextArea)findByName("smsToSendTextArea", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findSmsPassword(Component root) {
        return (com.codename1.ui.TextField)findByName("smsPassword", root);
    }

    public com.codename1.ui.TextField findSmsPassword() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("smsPassword", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("smsPassword", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findRegisterUserButton(Component root) {
        return (com.codename1.ui.Button)findByName("registerUserButton", root);
    }

    public com.codename1.ui.Button findRegisterUserButton() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("registerUserButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("registerUserButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findLabel(Component root) {
        return (com.codename1.ui.Label)findByName("Label", root);
    }

    public com.codename1.ui.Label findLabel() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("Label", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("Label", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findDisplayName(Component root) {
        return (com.codename1.ui.Label)findByName("displayName", root);
    }

    public com.codename1.ui.Label findDisplayName() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("displayName", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("displayName", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findSmsSenderTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("smsSenderTextField", root);
    }

    public com.codename1.ui.TextField findSmsSenderTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("smsSenderTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("smsSenderTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    protected void exitForm(Form f) {
        if("AvaillableGroups".equals(f.getName())) {
            exitAvaillableGroups(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("ResetMyPassword".equals(f.getName())) {
            exitResetMyPassword(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("LoginUser".equals(f.getName())) {
            exitLoginUser(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("ContactsRenderer".equals(f.getName())) {
            exitContactsRenderer(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(f.getName())) {
            exitMain(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("PhoneContacts".equals(f.getName())) {
            exitPhoneContacts(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("SignUp".equals(f.getName())) {
            exitSignUp(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("GroupDisplay".equals(f.getName())) {
            exitGroupDisplay(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("InformationForm".equals(f.getName())) {
            exitInformationForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("SMStoGroup".equals(f.getName())) {
            exitSMStoGroup(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("SplashScreen".equals(f.getName())) {
            exitSplashScreen(f);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void exitAvaillableGroups(Form f) {
    }


    protected void exitResetMyPassword(Form f) {
    }


    protected void exitLoginUser(Form f) {
    }


    protected void exitContactsRenderer(Form f) {
    }


    protected void exitMain(Form f) {
    }


    protected void exitPhoneContacts(Form f) {
    }


    protected void exitSignUp(Form f) {
    }


    protected void exitGroupDisplay(Form f) {
    }


    protected void exitInformationForm(Form f) {
    }


    protected void exitSMStoGroup(Form f) {
    }


    protected void exitSplashScreen(Form f) {
    }

    protected void beforeShow(Form f) {
    aboutToShowThisContainer = f;
        if("AvaillableGroups".equals(f.getName())) {
            beforeAvaillableGroups(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("ResetMyPassword".equals(f.getName())) {
            beforeResetMyPassword(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("LoginUser".equals(f.getName())) {
            beforeLoginUser(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("ContactsRenderer".equals(f.getName())) {
            beforeContactsRenderer(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(f.getName())) {
            beforeMain(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("PhoneContacts".equals(f.getName())) {
            beforePhoneContacts(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("SignUp".equals(f.getName())) {
            beforeSignUp(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("GroupDisplay".equals(f.getName())) {
            beforeGroupDisplay(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("InformationForm".equals(f.getName())) {
            beforeInformationForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("SMStoGroup".equals(f.getName())) {
            beforeSMStoGroup(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("SplashScreen".equals(f.getName())) {
            beforeSplashScreen(f);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void beforeAvaillableGroups(Form f) {
    }


    protected void beforeResetMyPassword(Form f) {
    }


    protected void beforeLoginUser(Form f) {
    }


    protected void beforeContactsRenderer(Form f) {
    }


    protected void beforeMain(Form f) {
    }


    protected void beforePhoneContacts(Form f) {
    }


    protected void beforeSignUp(Form f) {
    }


    protected void beforeGroupDisplay(Form f) {
    }


    protected void beforeInformationForm(Form f) {
    }


    protected void beforeSMStoGroup(Form f) {
    }


    protected void beforeSplashScreen(Form f) {
    }

    protected void beforeShowContainer(Container c) {
        aboutToShowThisContainer = c;
        if("AvaillableGroups".equals(c.getName())) {
            beforeContainerAvaillableGroups(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("ResetMyPassword".equals(c.getName())) {
            beforeContainerResetMyPassword(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("LoginUser".equals(c.getName())) {
            beforeContainerLoginUser(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("ContactsRenderer".equals(c.getName())) {
            beforeContainerContactsRenderer(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(c.getName())) {
            beforeContainerMain(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("PhoneContacts".equals(c.getName())) {
            beforeContainerPhoneContacts(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("SignUp".equals(c.getName())) {
            beforeContainerSignUp(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("GroupDisplay".equals(c.getName())) {
            beforeContainerGroupDisplay(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("InformationForm".equals(c.getName())) {
            beforeContainerInformationForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("SMStoGroup".equals(c.getName())) {
            beforeContainerSMStoGroup(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("SplashScreen".equals(c.getName())) {
            beforeContainerSplashScreen(c);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void beforeContainerAvaillableGroups(Container c) {
    }


    protected void beforeContainerResetMyPassword(Container c) {
    }


    protected void beforeContainerLoginUser(Container c) {
    }


    protected void beforeContainerContactsRenderer(Container c) {
    }


    protected void beforeContainerMain(Container c) {
    }


    protected void beforeContainerPhoneContacts(Container c) {
    }


    protected void beforeContainerSignUp(Container c) {
    }


    protected void beforeContainerGroupDisplay(Container c) {
    }


    protected void beforeContainerInformationForm(Container c) {
    }


    protected void beforeContainerSMStoGroup(Container c) {
    }


    protected void beforeContainerSplashScreen(Container c) {
    }

    protected void postShow(Form f) {
        if("AvaillableGroups".equals(f.getName())) {
            postAvaillableGroups(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("ResetMyPassword".equals(f.getName())) {
            postResetMyPassword(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("LoginUser".equals(f.getName())) {
            postLoginUser(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("ContactsRenderer".equals(f.getName())) {
            postContactsRenderer(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(f.getName())) {
            postMain(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("PhoneContacts".equals(f.getName())) {
            postPhoneContacts(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("SignUp".equals(f.getName())) {
            postSignUp(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("GroupDisplay".equals(f.getName())) {
            postGroupDisplay(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("InformationForm".equals(f.getName())) {
            postInformationForm(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("SMStoGroup".equals(f.getName())) {
            postSMStoGroup(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("SplashScreen".equals(f.getName())) {
            postSplashScreen(f);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void postAvaillableGroups(Form f) {
    }


    protected void postResetMyPassword(Form f) {
    }


    protected void postLoginUser(Form f) {
    }


    protected void postContactsRenderer(Form f) {
    }


    protected void postMain(Form f) {
    }


    protected void postPhoneContacts(Form f) {
    }


    protected void postSignUp(Form f) {
    }


    protected void postGroupDisplay(Form f) {
    }


    protected void postInformationForm(Form f) {
    }


    protected void postSMStoGroup(Form f) {
    }


    protected void postSplashScreen(Form f) {
    }

    protected void postShowContainer(Container c) {
        if("AvaillableGroups".equals(c.getName())) {
            postContainerAvaillableGroups(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("ResetMyPassword".equals(c.getName())) {
            postContainerResetMyPassword(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("LoginUser".equals(c.getName())) {
            postContainerLoginUser(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("ContactsRenderer".equals(c.getName())) {
            postContainerContactsRenderer(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(c.getName())) {
            postContainerMain(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("PhoneContacts".equals(c.getName())) {
            postContainerPhoneContacts(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("SignUp".equals(c.getName())) {
            postContainerSignUp(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("GroupDisplay".equals(c.getName())) {
            postContainerGroupDisplay(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("InformationForm".equals(c.getName())) {
            postContainerInformationForm(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("SMStoGroup".equals(c.getName())) {
            postContainerSMStoGroup(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("SplashScreen".equals(c.getName())) {
            postContainerSplashScreen(c);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void postContainerAvaillableGroups(Container c) {
    }


    protected void postContainerResetMyPassword(Container c) {
    }


    protected void postContainerLoginUser(Container c) {
    }


    protected void postContainerContactsRenderer(Container c) {
    }


    protected void postContainerMain(Container c) {
    }


    protected void postContainerPhoneContacts(Container c) {
    }


    protected void postContainerSignUp(Container c) {
    }


    protected void postContainerGroupDisplay(Container c) {
    }


    protected void postContainerInformationForm(Container c) {
    }


    protected void postContainerSMStoGroup(Container c) {
    }


    protected void postContainerSplashScreen(Container c) {
    }

    protected void onCreateRoot(String rootName) {
        if("AvaillableGroups".equals(rootName)) {
            onCreateAvaillableGroups();
            aboutToShowThisContainer = null;
            return;
        }

        if("ResetMyPassword".equals(rootName)) {
            onCreateResetMyPassword();
            aboutToShowThisContainer = null;
            return;
        }

        if("LoginUser".equals(rootName)) {
            onCreateLoginUser();
            aboutToShowThisContainer = null;
            return;
        }

        if("ContactsRenderer".equals(rootName)) {
            onCreateContactsRenderer();
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(rootName)) {
            onCreateMain();
            aboutToShowThisContainer = null;
            return;
        }

        if("PhoneContacts".equals(rootName)) {
            onCreatePhoneContacts();
            aboutToShowThisContainer = null;
            return;
        }

        if("SignUp".equals(rootName)) {
            onCreateSignUp();
            aboutToShowThisContainer = null;
            return;
        }

        if("GroupDisplay".equals(rootName)) {
            onCreateGroupDisplay();
            aboutToShowThisContainer = null;
            return;
        }

        if("InformationForm".equals(rootName)) {
            onCreateInformationForm();
            aboutToShowThisContainer = null;
            return;
        }

        if("SMStoGroup".equals(rootName)) {
            onCreateSMStoGroup();
            aboutToShowThisContainer = null;
            return;
        }

        if("SplashScreen".equals(rootName)) {
            onCreateSplashScreen();
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void onCreateAvaillableGroups() {
    }


    protected void onCreateResetMyPassword() {
    }


    protected void onCreateLoginUser() {
    }


    protected void onCreateContactsRenderer() {
    }


    protected void onCreateMain() {
    }


    protected void onCreatePhoneContacts() {
    }


    protected void onCreateSignUp() {
    }


    protected void onCreateGroupDisplay() {
    }


    protected void onCreateInformationForm() {
    }


    protected void onCreateSMStoGroup() {
    }


    protected void onCreateSplashScreen() {
    }

    protected Hashtable getFormState(Form f) {
        Hashtable h = super.getFormState(f);
        if("AvaillableGroups".equals(f.getName())) {
            getStateAvaillableGroups(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("ResetMyPassword".equals(f.getName())) {
            getStateResetMyPassword(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("LoginUser".equals(f.getName())) {
            getStateLoginUser(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("ContactsRenderer".equals(f.getName())) {
            getStateContactsRenderer(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Main".equals(f.getName())) {
            getStateMain(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("PhoneContacts".equals(f.getName())) {
            getStatePhoneContacts(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("SignUp".equals(f.getName())) {
            getStateSignUp(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("GroupDisplay".equals(f.getName())) {
            getStateGroupDisplay(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("InformationForm".equals(f.getName())) {
            getStateInformationForm(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("SMStoGroup".equals(f.getName())) {
            getStateSMStoGroup(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("SplashScreen".equals(f.getName())) {
            getStateSplashScreen(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

            return h;
    }


    protected void getStateAvaillableGroups(Form f, Hashtable h) {
    }


    protected void getStateResetMyPassword(Form f, Hashtable h) {
    }


    protected void getStateLoginUser(Form f, Hashtable h) {
    }


    protected void getStateContactsRenderer(Form f, Hashtable h) {
    }


    protected void getStateMain(Form f, Hashtable h) {
    }


    protected void getStatePhoneContacts(Form f, Hashtable h) {
    }


    protected void getStateSignUp(Form f, Hashtable h) {
    }


    protected void getStateGroupDisplay(Form f, Hashtable h) {
    }


    protected void getStateInformationForm(Form f, Hashtable h) {
    }


    protected void getStateSMStoGroup(Form f, Hashtable h) {
    }


    protected void getStateSplashScreen(Form f, Hashtable h) {
    }

    protected void setFormState(Form f, Hashtable state) {
        super.setFormState(f, state);
        if("AvaillableGroups".equals(f.getName())) {
            setStateAvaillableGroups(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("ResetMyPassword".equals(f.getName())) {
            setStateResetMyPassword(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("LoginUser".equals(f.getName())) {
            setStateLoginUser(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("ContactsRenderer".equals(f.getName())) {
            setStateContactsRenderer(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(f.getName())) {
            setStateMain(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("PhoneContacts".equals(f.getName())) {
            setStatePhoneContacts(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("SignUp".equals(f.getName())) {
            setStateSignUp(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("GroupDisplay".equals(f.getName())) {
            setStateGroupDisplay(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("InformationForm".equals(f.getName())) {
            setStateInformationForm(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("SMStoGroup".equals(f.getName())) {
            setStateSMStoGroup(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("SplashScreen".equals(f.getName())) {
            setStateSplashScreen(f, state);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void setStateAvaillableGroups(Form f, Hashtable state) {
    }


    protected void setStateResetMyPassword(Form f, Hashtable state) {
    }


    protected void setStateLoginUser(Form f, Hashtable state) {
    }


    protected void setStateContactsRenderer(Form f, Hashtable state) {
    }


    protected void setStateMain(Form f, Hashtable state) {
    }


    protected void setStatePhoneContacts(Form f, Hashtable state) {
    }


    protected void setStateSignUp(Form f, Hashtable state) {
    }


    protected void setStateGroupDisplay(Form f, Hashtable state) {
    }


    protected void setStateInformationForm(Form f, Hashtable state) {
    }


    protected void setStateSMStoGroup(Form f, Hashtable state) {
    }


    protected void setStateSplashScreen(Form f, Hashtable state) {
    }

    protected boolean setListModel(List cmp) {
        String listName = cmp.getName();
        if("groupContactsList".equals(listName)) {
            return initListModelGroupContactsList(cmp);
        }
        if("phoneContactsList".equals(listName)) {
            return initListModelPhoneContactsList(cmp);
        }
        return super.setListModel(cmp);
    }

    protected boolean initListModelGroupContactsList(List cmp) {
        return false;
    }

    protected boolean initListModelPhoneContactsList(List cmp) {
        return false;
    }

    protected void handleComponentAction(Component c, ActionEvent event) {
        Container rootContainerAncestor = getRootAncestor(c);
        if(rootContainerAncestor == null) return;
        String rootContainerName = rootContainerAncestor.getName();
        Container leadParentContainer = c.getParent().getLeadParent();
        if(leadParentContainer != null && leadParentContainer.getClass() != Container.class) {
            c = c.getParent().getLeadParent();
        }
        if(rootContainerName == null) return;
        if(rootContainerName.equals("AvaillableGroups")) {
            if("myFamilyGroup".equals(c.getName())) {
                onAvaillableGroups_MyFamilyGroupAction(c, event);
                return;
            }
            if("myFriendsGroup".equals(c.getName())) {
                onAvaillableGroups_MyFriendsGroupAction(c, event);
                return;
            }
            if("myCoWorkersGroup".equals(c.getName())) {
                onAvaillableGroups_MyCoWorkersGroupAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("ResetMyPassword")) {
            if("emailToReset".equals(c.getName())) {
                onResetMyPassword_EmailToResetAction(c, event);
                return;
            }
            if("resetDePassword".equals(c.getName())) {
                onResetMyPassword_ResetDePasswordAction(c, event);
                return;
            }
            if("cancel".equals(c.getName())) {
                onResetMyPassword_CancelAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("LoginUser")) {
            if("smsUsername".equals(c.getName())) {
                onLoginUser_SmsUsernameAction(c, event);
                return;
            }
            if("smsPassword".equals(c.getName())) {
                onLoginUser_SmsPasswordAction(c, event);
                return;
            }
            if("loginButton".equals(c.getName())) {
                onLoginUser_LoginButtonAction(c, event);
                return;
            }
            if("cancel".equals(c.getName())) {
                onLoginUser_CancelAction(c, event);
                return;
            }
            if("resetPasswordButton".equals(c.getName())) {
                onLoginUser_ResetPasswordButtonAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("Main")) {
            if("sendSMSButton".equals(c.getName())) {
                onMain_SendSMSButtonAction(c, event);
                return;
            }
            if("smsToSendTextArea".equals(c.getName())) {
                onMain_SmsToSendTextAreaAction(c, event);
                return;
            }
            if("numbersToSendSMSTextArea".equals(c.getName())) {
                onMain_NumbersToSendSMSTextAreaAction(c, event);
                return;
            }
            if("addNumbersButton".equals(c.getName())) {
                onMain_AddNumbersButtonAction(c, event);
                return;
            }
            if("smsSenderTextField".equals(c.getName())) {
                onMain_SmsSenderTextFieldAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("PhoneContacts")) {
            if("phoneContactsList".equals(c.getName())) {
                onPhoneContacts_PhoneContactsListAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("SignUp")) {
            if("usernameTextField".equals(c.getName())) {
                onSignUp_UsernameTextFieldAction(c, event);
                return;
            }
            if("passwordTextField".equals(c.getName())) {
                onSignUp_PasswordTextFieldAction(c, event);
                return;
            }
            if("confirmPasswordTextField".equals(c.getName())) {
                onSignUp_ConfirmPasswordTextFieldAction(c, event);
                return;
            }
            if("emailTextField".equals(c.getName())) {
                onSignUp_EmailTextFieldAction(c, event);
                return;
            }
            if("registerUserButton".equals(c.getName())) {
                onSignUp_RegisterUserButtonAction(c, event);
                return;
            }
            if("signInButton".equals(c.getName())) {
                onSignUp_SignInButtonAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("GroupDisplay")) {
            if("groupContactsList".equals(c.getName())) {
                onGroupDisplay_GroupContactsListAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("InformationForm")) {
            if("firstTextArea".equals(c.getName())) {
                onInformationForm_FirstTextAreaAction(c, event);
                return;
            }
            if("infoTextArea".equals(c.getName())) {
                onInformationForm_InfoTextAreaAction(c, event);
                return;
            }
            if("infoNumbersTextArea".equals(c.getName())) {
                onInformationForm_InfoNumbersTextAreaAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("SMStoGroup")) {
            if("groupSMSTextArea".equals(c.getName())) {
                onSMStoGroup_GroupSMSTextAreaAction(c, event);
                return;
            }
            if("groupSenderTextField".equals(c.getName())) {
                onSMStoGroup_GroupSenderTextFieldAction(c, event);
                return;
            }
            if("sendSMStoGroupButton".equals(c.getName())) {
                onSMStoGroup_SendSMStoGroupButtonAction(c, event);
                return;
            }
        }
    }

      protected void onAvaillableGroups_MyFamilyGroupAction(Component c, ActionEvent event) {
      }

      protected void onAvaillableGroups_MyFriendsGroupAction(Component c, ActionEvent event) {
      }

      protected void onAvaillableGroups_MyCoWorkersGroupAction(Component c, ActionEvent event) {
      }

      protected void onResetMyPassword_EmailToResetAction(Component c, ActionEvent event) {
      }

      protected void onResetMyPassword_ResetDePasswordAction(Component c, ActionEvent event) {
      }

      protected void onResetMyPassword_CancelAction(Component c, ActionEvent event) {
      }

      protected void onLoginUser_SmsUsernameAction(Component c, ActionEvent event) {
      }

      protected void onLoginUser_SmsPasswordAction(Component c, ActionEvent event) {
      }

      protected void onLoginUser_LoginButtonAction(Component c, ActionEvent event) {
      }

      protected void onLoginUser_CancelAction(Component c, ActionEvent event) {
      }

      protected void onLoginUser_ResetPasswordButtonAction(Component c, ActionEvent event) {
      }

      protected void onMain_SendSMSButtonAction(Component c, ActionEvent event) {
      }

      protected void onMain_SmsToSendTextAreaAction(Component c, ActionEvent event) {
      }

      protected void onMain_NumbersToSendSMSTextAreaAction(Component c, ActionEvent event) {
      }

      protected void onMain_AddNumbersButtonAction(Component c, ActionEvent event) {
      }

      protected void onMain_SmsSenderTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onPhoneContacts_PhoneContactsListAction(Component c, ActionEvent event) {
      }

      protected void onSignUp_UsernameTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onSignUp_PasswordTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onSignUp_ConfirmPasswordTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onSignUp_EmailTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onSignUp_RegisterUserButtonAction(Component c, ActionEvent event) {
      }

      protected void onSignUp_SignInButtonAction(Component c, ActionEvent event) {
      }

      protected void onGroupDisplay_GroupContactsListAction(Component c, ActionEvent event) {
      }

      protected void onInformationForm_FirstTextAreaAction(Component c, ActionEvent event) {
      }

      protected void onInformationForm_InfoTextAreaAction(Component c, ActionEvent event) {
      }

      protected void onInformationForm_InfoNumbersTextAreaAction(Component c, ActionEvent event) {
      }

      protected void onSMStoGroup_GroupSMSTextAreaAction(Component c, ActionEvent event) {
      }

      protected void onSMStoGroup_GroupSenderTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onSMStoGroup_SendSMStoGroupButtonAction(Component c, ActionEvent event) {
      }

}
