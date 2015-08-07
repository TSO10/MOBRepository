Documentation for Handin2

Issue1:
We has an issue to update the seekbar when song is in background. 
We thought to update it after the user open the application again but for some reason 
seekbar could not be updated. We solved the issue by using a Runnable in the service class,
which update the current position of song every 100 millisec. 

Issue2: 
We are using the service in different activities, 
which means we implement ServiceConnection multiple 
times and that is not so smart. In future we should create a class which takes care of this. 

Issue 3: 
Because of time pressure naming of functions and variables 
are not so well defined. Better  naming would give better code understanding 

Issue 4: 
we got nullpointer exception in case of using only one mediaplayer 
for both MainActivity and the service. The solution was to create new instance of mediaplayer in MainActivity

Issue 5: 

ListView
Precondition: The Song objects were stored in an ArrayList, and
the ArrayAdapter were initialized.
What: Storing the Song objects in TextView objects inside a
ListView. The ArrayAdapter required the resource to be of type
TextView.
Caused by: java.lang.ClassCastException:
android.widget.LinearLayout cannot be cast to
android.widget.TextView
java.lang.IllegalStateException: ArrayAdapter requires
the resource ID to be a TextView
When: The last part of the assignment when the layout of the
second activity where to be made (displaying the songs picked up
locally into a list).
Succes: The LinearLayout containing the TextView object were
deleted in the item.xml file.
