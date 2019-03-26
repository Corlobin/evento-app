package uvv.com.br.uvvapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import uvv.com.br.uvvapp.handle.BarcodeProcessor;
import uvv.com.br.uvvapp.handle.CameraCallback;

public class QRCodeActivity extends AppCompatActivity {

    private SurfaceView surfaceView;
    public CameraSource cameraSource;
    public TextView textView;
    public BarcodeDetector barcodeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        surfaceView = findViewById(R.id.camera);
        textView = findViewById(R.id.textView);

        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE).build();
        cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setRequestedPreviewSize(640, 480).build();

        CameraCallback cameraCallback = new CameraCallback(this);
        BarcodeProcessor barcodeProcessor = new BarcodeProcessor(this);

        surfaceView.getHolder().addCallback(cameraCallback);
        barcodeDetector.setProcessor(barcodeProcessor);
    }
}
