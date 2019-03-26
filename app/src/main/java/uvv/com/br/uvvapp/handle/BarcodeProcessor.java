package uvv.com.br.uvvapp.handle;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.SparseArray;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;

import uvv.com.br.uvvapp.ConfirmarActivity;
import uvv.com.br.uvvapp.QRCodeActivity;

public class BarcodeProcessor implements Detector.Processor<Barcode> {
    public static boolean calledOne = false;

    private QRCodeActivity qrCodeActivity;

    public BarcodeProcessor(QRCodeActivity qrCodeActivity) {
        this.qrCodeActivity = qrCodeActivity;
    }

    @Override
    public void release() {
        calledOne = false;
    }

    @Override
    public void receiveDetections(Detector.Detections<Barcode> detections) {
        final SparseArray<Barcode> barcodeSparseArray = detections.getDetectedItems();
        if (barcodeSparseArray.size() != 0) {
            this.qrCodeActivity.textView.post(new Runnable() {
                @Override
                public void run() {
                    Vibrator vibrator = (Vibrator) qrCodeActivity.getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(1000);

                    String texto = barcodeSparseArray.valueAt(0).displayValue;
                    if ("SimpósioUVV2019".equals(texto)) {
                        qrCodeActivity.textView.setText("Bem vindo ao I Simpósio Cardiovascular 2019!");
                        return;
                    }
                }
            });
            if (calledOne == false) {
                Intent intent = new Intent(qrCodeActivity, ConfirmarActivity.class);
                qrCodeActivity.startActivity(intent);
                calledOne = true;
            }
        }
    }
}
