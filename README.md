

# Video Cutter [Demo]
This android app aims to do some advance features on a dummy video like cut, trim, save etc. Note that, this app is neither finised yet nor it is full functioning.

### Overview
- This app is created on the concept of FFmpeg library.
- Takes a sample video from apps's very own 'assets' folder.
- The app then creates temporary folder in its cache directory (as you cannot purse any video from **assets** folder directly as a file, thus you cannot get any Uri from assets videos).
- In a custom layout the app then plays the video with a recyclerview full of snaps/thumbnails taken from the the video (1000 ms interval).
-- Recyclerview gives the advantage to scroll left or right a start a marking for **cut start** point.
-- You then put another mark as **cut stop** point and touch the selected area. When you touch the selected area (bounded by two or multiple cut points) the full area is marked as red rectangle and lets you do some operation by pressing the save button (white check mark button at the top right corner).
- The files then saved in your local storage (in this case **video_cutter_demo**) and played back to same activity layout. (This feature has issues which is discussed in **Known Issue** portion of this readme file)
- To avoid redundacy this app didn't focus on features like:
-- Databindings
-- Dependency Injecttion (Dagger 2)
-- RxJava & Retrofit
-- MVVM / MVP or any popular design patterns
- But again if you want to see how the mentioned technologies are being implemented in modern android apps, please visit this [repo](https://github.com/tcse9/GoGet-Weather-App) designed and implemented by the author. 
- **You may find this app is not fully functional but you can have an idea that the concept of video trimming/cropping can be implemented in android platform with the help ok libraries like FFmpeg, FFmpeg-Android etc.**

## Known issues
- When you cut the video and try to merge back, according to the implementation the merged video will be loaded in the same place but in this case the .mp4 files (after merging) are not being created. It is a known issue (cpu dependent i.e. arm, X86 etc) from ffmepg and is reported by many communities. For a better understanding visit [this](https://github.com/WritingMinds/ffmpeg-android-java/issues/141) and [this](https://issuetracker.google.com/issues/37067983) links. For this reason you may end up getting result like the screen shot given below:

<img src="/screenshots/cannot_play_issue.png" alt="drawing" width="200"/>
- App may lack layout design smoothness.
- The buttons/views other than arow back button, play-pause btton and save button (white checkmark button at top right) are now don't have any action, rather they are represented to resemble the mockup given.
- After you complete a full play session by pressing the play button, sometimes video doesn't play if you press the play button again.

## Study element / future plan
- "A complete, cross-platform solution to record, convert and stream audio and video." -from [FFmpeg official site](https://www.ffmpeg.org/). So there are lot of possibilities to take advantage and make interactive app for application devs.
- Next target would be to colplete this app asap including following features:
- Alongside video audio crop/trim functionality can be added.

## How to use
Just follow the instruction given by following screen shot


<img src="/screenshots/main_page.png" alt="drawing" width="200"/>
