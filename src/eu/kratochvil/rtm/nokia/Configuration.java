package eu.kratochvil.rtm.nokia;

import com.sun.lwuit.util.Log;
import javax.microedition.rms.RecordStore;

/**
 *
 * @author jirikratochvil
 */
public class Configuration {

    String storeName = null;

    RecordStore rsPrefs = null;

    boolean opened = false;

    public Configuration(String storeName) {
        this.storeName = storeName;
    }

    public void openRecStore() {
        if (opened) {
            Log.p("Store is already opened", Log.WARNING);
            return ;
        }
        try {
            rsPrefs = RecordStore.openRecordStore(storeName, true);
            opened = true;
        } catch (Exception e) {
            Log.p("Error opening persistent storage", Log.ERROR);
            opened = false;
        }
    }

    public String readRecordAsString(String key, String defaultValue) {
        if (!opened) {
            Log.p("Can't read closed store", Log.WARNING);
            throw new IllegalStateException("Can't read closed store");
        }
        try {
            int len;
            for (int i = 1; i <= rsPrefs.getNumRecords(); i++) {
                byte[] recData = new byte[rsPrefs.getRecordSize(i)];
                len = rsPrefs.getRecord(i, recData, 0);
                String record = new String(recData, 0, len);

                int equalsCharacterPosition = record.indexOf("=");

                String keyInRecord = record.substring(0, equalsCharacterPosition);
                if (keyInRecord.equalsIgnoreCase(key)) {
                    return record.substring(equalsCharacterPosition + 1);
                }
            }
            return defaultValue;
        } catch (Exception e) {
            Log.p("Error reading configuration" + e.getMessage(), Log.ERROR);
            throw new RuntimeException("Error reading configuration");
        }
    }
    public boolean readRecordAsBoolean(String key, boolean defaultValue) {
        return "true".equalsIgnoreCase(readRecordAsString(key, defaultValue?"true":"false"));
    }
    public boolean readRecordAsBoolean(String key) {
        return readRecordAsBoolean(key, false);
    }
    public String readRecordAsString(String key) {
        return readRecordAsString(key, null);
    }

    public void readRecords() {
        if (!opened) {
            Log.p("Can't read closed store", Log.WARNING);
            return ;
        }
        try {
            // Intentionally make this too small to test code below
            byte[] recData = new byte[5];
            int len;

            for (int i = 1; i <= rsPrefs.getNumRecords(); i++) {
                if (rsPrefs.getRecordSize(i) > recData.length) {
                    recData = new byte[rsPrefs.getRecordSize(i)];
                }

                len = rsPrefs.getRecord(i, recData, 0);
                Log.p("Record #" + i + ": " + new String(recData, 0, len));
            }
        } catch (Exception e) {
            Log.p("Error reading configuration", Log.ERROR);
        }
    }

    public void closeRecStore() {
        if (!opened) {
            Log.p("Store is already closed", Log.WARNING);
            return ;
        }
        try {
            if (rsPrefs != null) {
                rsPrefs.closeRecordStore();
                opened = false;
            }
        } catch (Exception e) {
            Log.p("Error saving properties (close store)" + e.getMessage(), Log.ERROR);
        }
    }

    public void deleteRecStore() {
        if (opened) {
            Log.p("Can't delete opened store", Log.WARNING);
        }
        if (RecordStore.listRecordStores() != null) {
            try {
                RecordStore.deleteRecordStore(storeName);
            } catch (Exception e) {
                Log.p("Error saving properties (delete store)" + e.getMessage(), Log.ERROR);
            }
        }
    }


    public void writeRecord(String key, boolean value) {
        writeRecord(key, value?"true":"false");
    }
    public void writeRecord(String key, int value) {
        writeRecord(key, new Integer(value).toString());
    }

    public void writeRecord(String key, String value) {
        if (!opened) {
            Log.p("Can't write into closed store", Log.WARNING);
            return ;
        }
        byte[] rec = (key + "=" + value).getBytes();
        try {
            rsPrefs.addRecord(rec, 0, rec.length);
        } catch (Exception e) {
            Log.p("Error saving record: " + key + " - " + e.getMessage(), Log.ERROR);
        }
    }
}
