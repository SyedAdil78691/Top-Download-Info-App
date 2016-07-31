package com.example.adilhussain.topdownloadinfo;

/**
 * Created by Adil Hussain on 7/29/2016.
 */
public interface appConstant {


    String[] Source = {"Top 10 Songs", "Top 25 Songs", "Top 10 Albums", "Top 25 Albums", "Top 10 New Releases", "Top 25 New Releases",
            "Top 10 Just Added", "Top 25 Just Added"
    };
//    , "Top 10 Featured", "Albums & Exclusives", "Top 25 Featured", "Albums & Exclusives",
//            "Top 10 Paid Apps", "Top 25 Paid Apps", " Top 10 Free Apps", " Top 25 Free Apps", "Top Movies", "Top TV Seasons",
//            "Top TV Episodes", "Press Info", "Apple Developer News", "Apple Server Documentation", "Latest Movie Trailers"};
//

    String[] SourceLink = {"http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=10/xml",
            "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=25/xml",
            "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topalbums/limit=10/xml",
            "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topalbums/limit=25/xml",
            "http://ax.itunes.apple.com/WebObjects/MZStore.woa/wpa/MRSS/newreleases/limit=10/rss.xml",
            "http://ax.itunes.apple.com/WebObjects/MZStore.woa/wpa/MRSS/newreleases/limit=25/rss.xml",
            "http://ax.itunes.apple.com/WebObjects/MZStore.woa/wpa/MRSS/justadded/limit=10/rss.xml",
            "http://ax.itunes.apple.com/WebObjects/MZStore.woa/wpa/MRSS/justadded/limit=25/rss.xml"
    };


    String Top_10_Songs = "File_Top_10_Songs.ser";
    String Top_25_Songs = "File_Top_25_Songs.ser";
    String Top_10_Albums = "File_10_Albums.ser";
    String Top_25_Albums = "File_25_Albums.ser";
    String Top_10_New_Releases = "File_25_New_Releases.ser";


}
