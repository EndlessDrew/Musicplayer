package endless.FindMusic;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Environment;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

import endless.MusicData.VideoData;

/**
 * Created by z on 2016/9/22.
 */
public class FindVideo {
    Cursor cursor ;

    public  List<VideoData> getVideo(ContentResolver contentResolver){
        cursor = contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,null,null,null,MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        List<VideoData> list = new ArrayList<VideoData>();
        for (int i =0;i<cursor.getCount();i++){
            VideoData data = new VideoData() ;
            cursor.moveToNext();
            long id = cursor.getColumnIndex(MediaStore.Video.Media._ID);
            String url = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.INTERNAL_CONTENT_URI.getPath()));
            String title = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.TITLE));
            String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.ARTIST));
            data.setTitle(title);
            data.setArtist(artist);
            data.setId(id);
            list.add(data);

        }
        return list;
    }



//  public  void getVideoFile(final List<VideoData> list, File file) {// 获得视频文件
//        allVideoList = new ArrayList<VideoData>();
//        getVideoFile(allVideoList, Environment.getExternalStorageDirectory());// 获得视频文件
//
//        file.listFiles(new FileFilter() {
//            @Override
//            public boolean accept(File file) {
//                // sdCard找到视频名称
//                String name = file.getName();
//
//                int i = name.indexOf('.');
//                if (i != -1) {
//                    name = name.substring(i);
//                    if (name.equalsIgnoreCase(".mp4")
//                            || name.equalsIgnoreCase(".3gp")
//                            || name.equalsIgnoreCase(".wmv")
//                            || name.equalsIgnoreCase(".ts")
//                            || name.equalsIgnoreCase(".rmvb")
//                            || name.equalsIgnoreCase(".mov")
//                            || name.equalsIgnoreCase(".m4v")
//                            || name.equalsIgnoreCase(".avi")
//                            || name.equalsIgnoreCase(".m3u8")
//                            || name.equalsIgnoreCase(".3gpp")
//                            || name.equalsIgnoreCase(".3gpp2")
//                            || name.equalsIgnoreCase(".mkv")
//                            || name.equalsIgnoreCase(".flv")
//                            || name.equalsIgnoreCase(".divx")
//                            || name.equalsIgnoreCase(".f4v")
//                            || name.equalsIgnoreCase(".rm")
//                            || name.equalsIgnoreCase(".asf")
//                            || name.equalsIgnoreCase(".ram")
//                            || name.equalsIgnoreCase(".mpg")
//                            || name.equalsIgnoreCase(".v8")
//                            || name.equalsIgnoreCase(".swf")
//                            || name.equalsIgnoreCase(".m2v")
//                            || name.equalsIgnoreCase(".asx")
//                            || name.equalsIgnoreCase(".ra")
//                            || name.equalsIgnoreCase(".ndivx")
//                            || name.equalsIgnoreCase(".xvid")) {
//                        VideoData vi = new VideoData();
//                        vi.setTitle(file.getName());
//                        vi.setUrl(file.getAbsolutePath());
//                        list.add(vi);
//                        return true;
//                    }
//                } else if (file.isDirectory()) {
//                    getVideoFile(list, file);
//                }
//                return false;
//            }
//        });
//    }
}
