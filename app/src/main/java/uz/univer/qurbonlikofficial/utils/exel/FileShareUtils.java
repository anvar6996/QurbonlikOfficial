package uz.univer.qurbonlikofficial.utils.exel;

import android.content.Context;
import android.net.Uri;

import androidx.core.content.FileProvider;

import java.io.File;


public class FileShareUtils {
    public static Uri accessFile(Context context, String fileName) {
        File file = new File(context.getExternalFilesDir(null), fileName);
        if (file.exists()) {
            return FileProvider.getUriForFile(context, "uz.univer.qurbonlikofficial.fileprovider", file);
        } else {
            return null;
        }
    }
}
