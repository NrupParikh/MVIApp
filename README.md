# MVIApp
Sample application to understand MVI architecture. (Model-View-Intent)

### Model-View-Intent

- In MVI, When user perform any action it is called as an Intent. So, it is a state which is an input to the model. Model store that state and then send the requested state to the View. View load the state from Model and display to the user.
- so, the data will always flow from the User and end with the user through intent.
- It is also called as an Uni-directional architecture.
- Every action contains the Intent.

### Intent->Model->View->User Display

- **Model**: Represent the state. Ex. Idle, Loading, Error, Change in the UI with any action, 
- **View**: Activity or Fragments are View. Which accept different Model states and display to user. It uses observable Intents to respond to user actions.
- **Intent**: It take user's action (Model) as an Input and show on UI.
