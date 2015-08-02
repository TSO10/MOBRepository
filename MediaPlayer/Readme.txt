This is a brief documentation for Handin 1. The mediaplayer contains 3 buttons, which control play, pause and stop events. 
Beside that there is a Seekbar to show status of the playing song. To update the Seekbar according to in which position the song is. 
To run the update on Seekbar the postDelayed method is called which executes a “Runnable” every 100 millisec. 
Playsong function takes care of four different scenarios to give a good user experience. In case the song is 
stopped it’s necessary to set the song source with calling “setDataSource” method on mediaplayer object. 
This is because “stop” method delete the songs source. 
ForwardSong function contains a listener to users click on seekbar to forward or reward the song by 
setting the progress of Seekbar to recent progress, plus mediaplayer is seeking to same progress.   
