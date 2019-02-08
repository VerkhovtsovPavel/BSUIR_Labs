package by.bsuir.mastering.pavelverk.android.hardwrite;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private SketchView mSketchView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        setContentView(R.layout.activity_main);
        mSketchView = (SketchView) findViewById(R.id.view_sketch);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_MENU:
                return true;
            case KeyEvent.KEYCODE_BACK:
                finish();
                return true;
            default:
                break;
        }
        return false;
    }

    public void clear(View view) {
        mSketchView.clear();
    }

    public void done(View view) {
        String features = mSketchView.getFeatures();
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Features")
                .setMessage(features)
                .setCancelable(false)
                .setNegativeButton("Ok, man",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
