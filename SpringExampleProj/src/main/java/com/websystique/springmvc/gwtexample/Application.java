package main.java.com.websystique.springmvc.gwtexample;

import java.math.BigDecimal;

import org.joda.time.LocalDate;

import main.java.com.websystique.springmvc.springexample.model.Employee;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
 
 
/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Application implements EntryPoint
{
 
 /**
  * The message displayed to the user when the server cannot be reached or
  * returns an error.
  */
 private static final String SERVER_ERROR = "An error occurred while "
   + "attempting to contact the server. Please check your network "
   + "connection and try again. The error is : ";
 
 /**
  * Create a remote service proxy to talk to the server-side Employee service.
  */
 private final EmployeeServiceGWTAsync employeeServiceGWT = EmployeeServiceGWTAsync.Util.getInstance();
 
 /**
  * This is the entry point method.
  */
 public void onModuleLoad() {
  final Button saveOrUpdateButton = new Button("SaveOrUpdate");
  final Button retrieveButton = new Button("Retrieve");
  final TextBox employeeInfoField = new TextBox();
  employeeInfoField.setText("Employee Info");
  final TextBox employeeIdField = new TextBox();
  final Label errorLabel = new Label();
 
  // We can add style names to widgets
  saveOrUpdateButton.addStyleName("sendButton");
  retrieveButton.addStyleName("sendButton");
 
  // Add the nameField and sendButton to the RootPanel
  // Use RootPanel.get() to get the entire body element
  RootPanel.get("employeeInfoFieldContainer").add(employeeInfoField);
  RootPanel.get("updateEmployeeButtonContainer").add(saveOrUpdateButton);
  RootPanel.get("employeeIdFieldContainer").add(employeeIdField);
  RootPanel.get("retrieveEmployeeButtonContainer").add(retrieveButton);
  RootPanel.get("errorLabelContainer").add(errorLabel);
 
  // Focus the cursor on the name field when the app loads
  employeeInfoField.setFocus(true);
  employeeInfoField.selectAll();
 
  // Create the popup dialog box
  final DialogBox dialogBox = new DialogBox();
  dialogBox.setText("Remote Procedure Call");
  dialogBox.setAnimationEnabled(true);
  final Button closeButton = new Button("Close");
  // We can set the id of a widget by accessing its Element
  closeButton.getElement().setId("closeButton");
  final Label textToServerLabel = new Label();
  final HTML serverResponseLabel = new HTML();
  VerticalPanel dialogVPanel = new VerticalPanel();
  dialogVPanel.addStyleName("dialogVPanel");
  dialogVPanel.add(new HTML("<b>Sending request to the server:</b>"));
  dialogVPanel.add(textToServerLabel);
  dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
  dialogVPanel.add(serverResponseLabel);
  dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
  dialogVPanel.add(closeButton);
  dialogBox.setWidget(dialogVPanel);
 
  // Add a handler to close the DialogBox
  closeButton.addClickHandler(new ClickHandler() {
   public void onClick(ClickEvent event) {
    dialogBox.hide();
    saveOrUpdateButton.setEnabled(true);
    saveOrUpdateButton.setFocus(true);
    retrieveButton.setEnabled(true);
   }
  });
 
  // Create a handler for the saveOrUpdateButton and employeeInfoField
  class SaveOrUpdateEmployeeHandler implements ClickHandler, KeyUpHandler {
   /**
    * Fired when the user clicks on the saveOrUpdateButton.
    */
   public void onClick(ClickEvent event) {
    sendEmployeeInfoToServer();
   }
 
   /**
    * Fired when the user types in the employeeInfoField.
    */
   public void onKeyUp(KeyUpEvent event) {
    if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
     sendEmployeeInfoToServer();
    }
   }
   /**
    * Send the employee info from the employeeInfoField to the server and wait for a response.
    */
   private void sendEmployeeInfoToServer() {
    // First, we validate the input.
    errorLabel.setText("");
    String textToServer = employeeInfoField.getText();
 
    // Then, we send the input to the server.
    saveOrUpdateButton.setEnabled(false);
    textToServerLabel.setText(textToServer);
    serverResponseLabel.setText("");
 
    String[] employeeInfo = textToServer.split(" ");
     
    int employeeId = Integer.parseInt(employeeInfo[0]);
    String employeeName = employeeInfo[1];
    String name = employeeInfo[2];
    String ssn = employeeInfo[3];
    LocalDate joiningDate  = LocalDate.parse(employeeInfo[4]);
    BigDecimal salary = BigDecimal.valueOf(Long.valueOf(employeeInfo[5]));
     
    employeeServiceGWT.saveOrUpdateEmployee(employeeId, employeeName, ssn, joiningDate, salary, new AsyncCallback<Void>() {
       public void onFailure(Throwable caught) {
        // Show the RPC error message to the user
        dialogBox
          .setText("Remote Procedure Call - Failure");
        serverResponseLabel
          .addStyleName("serverResponseLabelError");
        serverResponseLabel.setHTML(SERVER_ERROR + caught.toString());
        dialogBox.center();
        closeButton.setFocus(true);
       }
 
       public void onSuccess(Void noAnswer) {
        dialogBox.setText("Remote Procedure Call");
        serverResponseLabel
          .removeStyleName("serverResponseLabelError");
        serverResponseLabel.setHTML("OK");
        dialogBox.center();
        closeButton.setFocus(true);
       }
      });
   }
  }
   
  // Create a handler for the retrieveButton and employeeIdField
  class RetrieveEmployeeHandler implements ClickHandler, KeyUpHandler {
   /**
    * Fired when the user clicks on the retrieveButton.
    */
   public void onClick(ClickEvent event) {
    sendEmployeeIdToServer();
   }
 
   /**
    * Fired when the user types in the employeeIdField.
    */
   public void onKeyUp(KeyUpEvent event) {
    if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
     sendEmployeeIdToServer();
    }
   }
 
   /**
    * Send the id from the employeeIdField to the server and wait for a response.
    */
   private void sendEmployeeIdToServer() {
    // First, we validate the input.
    errorLabel.setText("");
    String textToServer = employeeIdField.getText();
 
    // Then, we send the input to the server.
    retrieveButton.setEnabled(false);
    textToServerLabel.setText(textToServer);
    serverResponseLabel.setText("");
 
    employeeServiceGWT.findEmployee(Integer.parseInt(textToServer), 
      new AsyncCallback<Employee>() {
       public void onFailure(Throwable caught) {
        // Show the RPC error message to the user
        dialogBox
          .setText("Remote Procedure Call - Failure");
        serverResponseLabel
          .addStyleName("serverResponseLabelError");
        serverResponseLabel.setHTML(SERVER_ERROR + caught.toString());
        dialogBox.center();
        closeButton.setFocus(true);
       }
 
       public void onSuccess(Employee employeeDTO) {
        dialogBox.setText("Remote Procedure Call");
        serverResponseLabel
          .removeStyleName("serverResponseLabelError");
        if(employeeDTO != null)
         serverResponseLabel.setHTML("Employee Information <br>Id : " + employeeDTO.getId() + "<br>Name : " + employeeDTO.getName() + "<br>Ssn : " + employeeDTO.getSsn() + "<br>Joining Date : " + employeeDTO.getJoiningDate() +"<br>Salary : " + employeeDTO.getSalary());
        else
         serverResponseLabel.setHTML("No employee with the specified id found");
        dialogBox.center();
        closeButton.setFocus(true);
       }
      });
   }
  }
 
  // Add a handler to send the employee info to the server
  SaveOrUpdateEmployeeHandler saveOrUpdateEmployeehandler = new SaveOrUpdateEmployeeHandler();
  saveOrUpdateButton.addClickHandler(saveOrUpdateEmployeehandler);
  employeeInfoField.addKeyUpHandler(saveOrUpdateEmployeehandler);
   
  // Add a handler to send the employee id to the server
  RetrieveEmployeeHandler retrieveEmployeehandler = new RetrieveEmployeeHandler();
  retrieveButton.addClickHandler(retrieveEmployeehandler);
  employeeIdField.addKeyUpHandler(retrieveEmployeehandler);
 }
 
}
