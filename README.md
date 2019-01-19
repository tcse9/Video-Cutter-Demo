

# Video Cutter [Demo]
This android app aims to do some advance features on a dummy video like cut, trim, save etc. Note that, this app is neitehr finised yet nor it is full functioning.
### Overview
- This app is created on the concept of FFmpeg library.
- Takes a sample video from apps's very own 'assets' folder.
- The app then creates temporary folder in its cache directory as you cannot purse any video from **assets** folder directly as a file thus you cannot get any Uri from assets videos.
- In a custom layout the app then plays the video with a recyclerview full of snaps/thumbnails takes from the the video (1000 ms interval).
-- Recyclerview gives user the advantage to scroll left or right a strart a marking for **cut start** point.
-- You then put another mark as **cut stop** pint and touch the selected area. When you touch the selected are (bounded by two or multiple cut points) then the full area is marked as red rectangle and lets you do some operation by pressing the button nearby the cut button.
- The files then saved in your local storage (in this case **video_cutter_demo**) and played back to same activity layout. (This feature has issues which is discussed in **Known Issue** portion of this readme file)
- To avoid redundacy this app didn't focus on features like:
-- Databindings
-- Dependency Injecttion (Dagger 2)
-- RxJava & Retrofit
-- MVVM / MVP or any popular design patterns
- But again if you want to see how the mentioned technologies are being implemented in modern android apps. please visit this [repo](https://github.com/tcse9/GoGet-Weather-App) designed and implemented by the author. 
- **You may find this app is not fully functional but you can have an idea that the concept of video trimming/cropping can be implemented in android platform with the help libraries like FFmpeg.**


## Known issues
- App sometimes crashses if the permission are not well maintained (i.e. user not allows them in runtime) or a previous version of the app is already installed in device. To avoid these, please uninstall any previous version of the app.
- When you cut the video and try to merge back, according to the implementation the merged video will be loaded in the same place but in this case the .mp4 files (after merging) are not being created. It is a known issue from ffmepg and is reported by many communities. For a better understanding visit [this](https://github.com/WritingMinds/ffmpeg-android-java/issues/141) and [this](https://issuetracker.google.com/issues/37067983) links.
- App may lack layout design smoothness as the main target was fulfilling the functionality first

## Study element / future plan
- FFmpeg the coolest library now-a-days. "A complete, cross-platform solution to record, convert and stream audio and video." -from [FFmpeg official site](https://www.ffmpeg.org/). So there are lot of possibilities to take advantage and make interactive app for application devs.
- Next target would be to colplete this app asap including following features:
-- Alongside video audio crop/trim functionality can be added.
-- Complete the ongoing tasks.
## How to use
- First according to the following screen shot put a **cut start** point byt tapping the cut button, and then scroll to somepoint and tap the cut button again to put a **cut stop** point.

![Alt text](/screenshots/main_page.png First according to the following screen shot put a 'cut start' point byt tapping the cut button, and then scroll to somepoint and tap the cut button again to put a 'cut stop' point.)screenshots/main_page.png

