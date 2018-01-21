# ListViewSample4Solstice
Simple ListView Example to get a contact list and modify it

This is a brief explanation about the project uploaded into this repo. ListView sample tries to get data and show the list using a custom adapter. 
In this document I'll tell the differents components


### Layout and Resources

activity_main_contacts.xml = layout where both listviews (favorites and non-favorites) are displayed
listview_item.xml = item for listview cell
contact_detail.xml = detail for contact selected 

#### Menu

contact_menu.xml = custom menu to add star button in order to set the change from favorite to non-favorite contact and vice versa.


### Android Folder (Java Classes)
Album = Object created to set custom adapter 
ContactDetail = Second Activity called after onClickEvent was triggered at MainActivity
ListViewAdapter = Custom Adapter to set images into ListViewCell
MainActivity = First activity of this app.

#### Utils
AsynkConnector = class extension of AsyncTask used to run the REST service in async mode.
Callback = Interface created to use this pattern after REST call
DataHandler = Class with static attributes used to get the contact selected into memory and avoid pass data through the intent (warning: just used to this project to gain time and let the thing will be easier :))
DataObject = To parse json and get the data processed.


