package com.example.estra.prueba;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Skeleton of an Android Things activity.
 * <p>
 * Android Things peripheral APIs are accessible through the class
 * PeripheralManagerService. For example, the snippet below will open a GPIO pin and
 * set it to HIGH:
 * <p>
 * <pre>{@code
 * PeripheralManagerService service = new PeripheralManagerService();
 * mLedGpio = service.openGpio("BCM6");
 * mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
 * mLedGpio.setValue(true);
 * }</pre>
 * <p>
 * For more complex peripherals, look for an existing user-space driver, or implement one if none
 * is available.
 *
 * @see <a href="https://github.com/androidthings/contrib-drivers#readme">https://github.com/androidthings/contrib-drivers#readme</a>
 */
public class MainActivity extends Activity {

    ListView lista;
    String[] values;
    Dpad mDpad;
    final static int UP       = 0;
    final static int LEFT     = 1;
    final static int RIGHT    = 2;
    final static int DOWN     = 3;
    final static int CENTER   = 4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDpad = new Dpad();

        values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2" };

        lista = (ListView)findViewById(R.id.lista);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, values);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, values[i],Toast.LENGTH_LONG).show();

            }
        });

        if(hasGamepad()){
            Log.i("TAG", "onCreate: conectado");
        }else{
            Log.i("TAG", "onCreate: desconectado");
        }
    }

    private boolean hasGamepad() {
        return getGameControllerIds().size() > 0;
    }

    private List<Integer> getGameControllerIds() {
        List<Integer> gameControllerDeviceIds = new ArrayList<>();

        int[] deviceIds = InputDevice.getDeviceIds();
        for (int deviceId : deviceIds) {
            InputDevice dev = InputDevice.getDevice(deviceId);
            int sources = dev.getSources();

            if (((sources & InputDevice.SOURCE_GAMEPAD) == InputDevice.SOURCE_GAMEPAD)
                    || ((sources & InputDevice.SOURCE_JOYSTICK)
                    == InputDevice.SOURCE_JOYSTICK)) {

                if (!gameControllerDeviceIds.contains(deviceId)) {
                    gameControllerDeviceIds.add(deviceId);
                }
            }
        }

        return gameControllerDeviceIds;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean handled = false;
        if ((event.getSource() & InputDevice.SOURCE_GAMEPAD)
                == InputDevice.SOURCE_GAMEPAD) {
            if (event.getRepeatCount() == 0) {

                if (keyCode == 96)
                    //Log.e("Taste:", "A presionada");

                if (keyCode == 97)
                    //Log.e("Taste:", "B presionada");

                if (keyCode == 99)
                    //Log.e("Taste:", "X presionada");

                if (keyCode == 100)
                    //Log.e("Taste:", "Y presionada");

                if (keyCode == 108)
                    //Log.e("Taste:", "START presionada");

                if (keyCode == event.getKeyCode())
                    Log.e("Taste:", "SELECT presionada"+event.getKeyCode());
            }
            if (handled) {
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        boolean handled = false;
        if ((event.getSource() & InputDevice.SOURCE_GAMEPAD)
                == InputDevice.SOURCE_GAMEPAD) {
            if (event.getRepeatCount() == 0) {

                if (keyCode == 96)
                    //Log.e("Taste:", "Square released");

                if (keyCode == 97)
                   // Log.e("Taste:", "X released");

                if (keyCode == 98){}
                   // Log.e("Taste:", "Circle released");
            }
            if (handled) {
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {

        // Check if this event if from a D-pad and process accordingly.
        if (Dpad.isDpadDevice(event)) {

            int press = mDpad.getDirectionPressed(event);
            switch (press) {
                case LEFT:
                   // Log.e("Taste:", "LEFT presionada");
                    return true;
                case RIGHT:
                    //Log.e("Taste:", "RIGHT presionada");
                    return true;
//

            }
        }
        return super.onGenericMotionEvent(event);
    }


//    @Override
//    public boolean onGenericMotionEvent(MotionEvent event) {
//
//
//        // Check that the event came from a game controller
//        if ((event.getSource() & InputDevice.SOURCE_JOYSTICK) ==
//                InputDevice.SOURCE_JOYSTICK &&
//                event.getAction() == MotionEvent.ACTION_MOVE) {
//
//
//            // Process all historical movement samples in the batch
//            final int historySize = event.getHistorySize();
//
//            // Process the movements starting from the
//            // earliest historical position in the batch
//            for (int i = 0; i < historySize; i++) {
//                // Process the event at historical position i
//                processJoystickInput(event, i);
//            }
//
//            // Process the current movement sample in the batch (position -1)
//            processJoystickInput(event, -1);
//            return true;
//        }
//        return super.onGenericMotionEvent(event);
//    }
//
//
//    private void processJoystickInput(MotionEvent event,
//                                      int historyPos) {
//
//        InputDevice mInputDevice = event.getDevice();
//
//        // Calculate the horizontal distance to move by
//        // using the input value from one of these physical controls:
//        // the left control stick, hat axis, or the right control stick.
//        float lx = getCenteredAxis(event, mInputDevice,
//                MotionEvent.AXIS_X, historyPos);
//
//        float rx = getCenteredAxis(event, mInputDevice,
//                MotionEvent.AXIS_Z, historyPos);
//
//        float ly = getCenteredAxis(event, mInputDevice,
//                MotionEvent.AXIS_Y, historyPos);
//
//        float ry = getCenteredAxis(event, mInputDevice,
//                MotionEvent.AXIS_RZ, historyPos);
//
//
//        //Log.i("LX:", lx + "");
//        //Log.e("LY:", ly + "");
//        //Log.i("RX:", rx + "");
//       // Log.w("RY:", ry + "");
//    }
//
//    private static float getCenteredAxis(MotionEvent event,
//                                         InputDevice device, int axis, int historyPos) {
//        final InputDevice.MotionRange range =
//                device.getMotionRange(axis, event.getSource());
//
//        // A joystick at rest does not always report an absolute position of
//        // (0,0). Use the getFlat() method to determine the range of values
//        // bounding the joystick axis center.
//        if (range != null) {
//            final float flat = range.getFlat();
//            final float value =
//                    historyPos < 0 ? event.getAxisValue(axis) :
//                            event.getHistoricalAxisValue(axis, historyPos);
//
//            // Ignore axis values that are within the 'flat' region of the
//            // joystick axis center.
//            if (Math.abs(value) > flat) {
//                return value;
//            }
//        }
//        return 0;
//    }

}
