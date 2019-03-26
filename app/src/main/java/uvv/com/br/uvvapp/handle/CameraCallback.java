package uvv.com.br.uvvapp.handle;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.view.SurfaceHolder;
import android.widget.Toast;

import uvv.com.br.uvvapp.QRCodeActivity;

public class CameraCallback implements SurfaceHolder.Callback {

    private QRCodeActivity qrCodeActivity;

    public CameraCallback(QRCodeActivity activity) {
        this.qrCodeActivity = activity;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (ActivityCompat.checkSelfPermission(qrCodeActivity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(qrCodeActivity, "É necessário habilitar a câmera para obter o QR CODE.", Toast.LENGTH_LONG).show();
            return;
        }
        try {
            this.qrCodeActivity.cameraSource.start(holder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        this.qrCodeActivity.cameraSource.stop();
        BarcodeProcessor.calledOne = false;
    }
}
