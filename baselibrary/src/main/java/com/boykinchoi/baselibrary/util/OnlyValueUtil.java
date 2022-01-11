package com.boykinchoi.baselibrary.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.Manifest.permission.ACCESS_WIFI_STATE;
import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public final class OnlyValueUtil {

    public static String getRightUniqueValue(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            String regEx = "[^(0-9)(A-Za-z):]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            String s = m.replaceAll("").replace(" ", "").toLowerCase();
            if (s.equals("unknown") || s.equals("0123456789abcdef")) {
                return null;
            } else {
                return s;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @SuppressLint("HardwareIds")
    public static String getAndroidID(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }


    /**
     * @return IMEI码，获取不到返回NULL
     */
    @SuppressLint({"MissingPermission", "HardwareIds"})
    @Nullable
    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager == null) {
            return null;
        }
        return telephonyManager.getDeviceId();
    }


    /**
     * @return MAC码，获取不到返回NULL
     */
    public @Nullable
    static String getMAC(Context context) {
        try {
            return DeviceUtils.getMacAddress(context);
//			return "11:22:33:44:55:66";
//			return "11:11:11:11:11:11";
//			return "22:22:22:22:22:22";
//			return "33:33:33:33:33:33";
//			return "55:55:55:55:55:66";
//			return "66:66:66:66:66:66";
//			return "77:77:77:77:77:77";
//			return "90:22:9a:da:51:b4";
//			return "90:22:9a:da:51:22";
//			return "46:72:0d:39:0d1f";
//			return "90:22:9a:da:51:11";
        } catch (Exception ignore) {
            return null;
        }
    }

    /**
     * @return SN码，获取不到返回NULL
     */
    @SuppressLint({"MissingPermission", "HardwareIds"})
    public @Nullable
    static String getSN() {
        String sn = Build.SERIAL;
        try {
            if (sn == null || sn.equalsIgnoreCase("unknown")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    sn = Build.getSerial();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sn;
    }

    /**
     * @return 返回生成的ID
     */
    public @NonNull
    static String getOtherID() {
        Random random = new Random(System.currentTimeMillis());
        return String.format(Locale.CHINESE, "GL%s%s", String.valueOf(random.nextLong()), String.valueOf(System.currentTimeMillis()));
    }

    /**
     * @return 返回一恒科SN号
     */
    private @Nullable
    @SuppressLint("PrivateApi")
    static String getSN_YHK() {
        String serial;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);
            serial = (String) get.invoke(c, "ro.serialnocustom");
        } catch (Exception ignore) {
            return null;
        }
        return serial;
    }

    /**
     * 获取小先序列号
     *
     * @return
     */
    public @Nullable
    @SuppressLint({"MissingPermission", "HardwareIds", "PrivateApi"})
    static String getSN_XX() {
        String serialNumber;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            serialNumber = Build.getSerial();
        } else {
            try {
                Class<?> c = Class.forName("android.os.SystemProperties");
                Method get = c.getMethod("get", String.class);
                serialNumber = (String) get.invoke(c, "ro.xiaoxian.no");
            } catch (Exception ignore) {
                return null;
            }
        }
        return serialNumber;
    }

    /**
     * @return 返回 GSM.Serial 序列号，获取不到返回NULL
     */
    private @Nullable
    static String getSN_GSM(Context context) {
        return getSystemArg(context, "gsm.serial", null);
    }

    /**
     * 获取系统String字段
     *
     * @param key 键值
     * @param def 如果获取失败默认返回的值
     * @return 获取得到的值
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @SuppressLint("PrivateApi")
    private static String getSystemArg(Context context, String key, String def) {
        String ret;
        try {
            ClassLoader cl = context.getClassLoader();
            Class SystemProperties = cl.loadClass("android.os.SystemProperties");
            Class[] paramTypes = new Class[2];
            paramTypes[0] = String.class;
            paramTypes[1] = String.class;
            Method get = SystemProperties.getMethod("get", paramTypes);
            Object[] params = new Object[2];
            params[0] = key;
            params[1] = def;
            ret = (String) get.invoke(SystemProperties, params);
        } catch (Exception ignore) {
            ret = def;
        }

        return ret;
    }


    private static final class ShellUtils {

        private static final String LINE_SEP = System.getProperty("line.separator");

        /**
         * Execute the command.
         *
         * @param command  The command.
         * @param isRooted True to use root, false otherwise.
         * @return the single {@link CommandResult} instance
         */
        public static CommandResult execCmd(final String command, final boolean isRooted) {
            return execCmd(new String[]{command}, isRooted, true);
        }

        /**
         * Execute the command.
         *
         * @param commands        The commands.
         * @param isRooted        True to use root, false otherwise.
         * @param isNeedResultMsg True to return the message of result, false otherwise.
         * @return the single {@link CommandResult} instance
         */
        public static CommandResult execCmd(final String[] commands,
                                            final boolean isRooted,
                                            final boolean isNeedResultMsg) {
            int result = -1;
            if (commands == null || commands.length == 0) {
                return new CommandResult(result, "", "");
            }
            Process process = null;
            BufferedReader successResult = null;
            BufferedReader errorResult = null;
            StringBuilder successMsg = null;
            StringBuilder errorMsg = null;
            DataOutputStream os = null;
            try {
                process = Runtime.getRuntime().exec(isRooted ? "su" : "sh");
                os = new DataOutputStream(process.getOutputStream());
                for (String command : commands) {
                    if (command == null) continue;
                    os.write(command.getBytes());
                    os.writeBytes(LINE_SEP);
                    os.flush();
                }
                os.writeBytes("exit" + LINE_SEP);
                os.flush();
                result = process.waitFor();
                if (isNeedResultMsg) {
                    successMsg = new StringBuilder();
                    errorMsg = new StringBuilder();
                    successResult = new BufferedReader(
                            new InputStreamReader(process.getInputStream(), "UTF-8")
                    );
                    errorResult = new BufferedReader(
                            new InputStreamReader(process.getErrorStream(), "UTF-8")
                    );
                    String line;
                    if ((line = successResult.readLine()) != null) {
                        successMsg.append(line);
                        while ((line = successResult.readLine()) != null) {
                            successMsg.append(LINE_SEP).append(line);
                        }
                    }
                    if ((line = errorResult.readLine()) != null) {
                        errorMsg.append(line);
                        while ((line = errorResult.readLine()) != null) {
                            errorMsg.append(LINE_SEP).append(line);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (os != null) {
                        os.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (successResult != null) {
                        successResult.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (errorResult != null) {
                        errorResult.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (process != null) {
                    process.destroy();
                }
            }
            return new CommandResult(
                    result,
                    successMsg == null ? "" : successMsg.toString(),
                    errorMsg == null ? "" : errorMsg.toString()
            );
        }

        /**
         * The result of command.
         */
        public static class CommandResult {
            public int result;
            public String successMsg;
            public String errorMsg;

            public CommandResult(final int result, final String successMsg, final String errorMsg) {
                this.result = result;
                this.successMsg = successMsg;
                this.errorMsg = errorMsg;
            }

            @Override
            public String toString() {
                return "result: " + result + "\n" +
                        "successMsg: " + successMsg + "\n" +
                        "errorMsg: " + errorMsg;
            }
        }
    }

    public static final class DeviceUtils {

        /**
         * Return the MAC address.
         * <p>Must hold
         * {@code <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />},
         * {@code <uses-permission android:name="android.permission.INTERNET" />}</p>
         *
         * @return the MAC address
         */
        @RequiresPermission(allOf = {ACCESS_WIFI_STATE, INTERNET})
        public static String getMacAddress(Context context) {
            return getMacAddress(context, (String[]) null);
        }

        /**
         * Return the MAC address.
         * <p>Must hold
         * {@code <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />},
         * {@code <uses-permission android:name="android.permission.INTERNET" />}</p>
         *
         * @return the MAC address
         */
        @RequiresPermission(allOf = {ACCESS_WIFI_STATE, INTERNET})
        private static String getMacAddress(Context context, final String... excepts) {
            String macAddress = getMacAddressByWifiInfo(context);
            if (isAddressNotInExcepts(macAddress, excepts)) {
                return macAddress;
            }
            macAddress = getMacAddressByNetworkInterface();
            if (isAddressNotInExcepts(macAddress, excepts)) {
                return macAddress;
            }
            macAddress = getMacAddressByInetAddress();
            if (isAddressNotInExcepts(macAddress, excepts)) {
                return macAddress;
            }
            macAddress = getMacAddressByFile();
            if (isAddressNotInExcepts(macAddress, excepts)) {
                return macAddress;
            }
            return "";
        }

        private static boolean isAddressNotInExcepts(final String address, final String... excepts) {
            if (excepts == null || excepts.length == 0) {
                return !"02:00:00:00:00:00".equals(address);
            }
            for (String filter : excepts) {
                if (address.equals(filter)) {
                    return false;
                }
            }
            return true;
        }

        @SuppressLint({"HardwareIds", "MissingPermission"})
        public static String getMacAddressByWifiInfo(Context context) {
            try {
                WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                if (wifi != null) {
                    WifiInfo info = wifi.getConnectionInfo();
                    if (info != null) return info.getMacAddress();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "02:00:00:00:00:00";
        }

        public static String getMacAddressByNetworkInterface() {
            try {
                Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
                while (nis.hasMoreElements()) {
                    NetworkInterface ni = nis.nextElement();
                    if (ni == null || !ni.getName().equalsIgnoreCase("wlan0")) continue;
                    byte[] macBytes = ni.getHardwareAddress();
                    if (macBytes != null && macBytes.length > 0) {
                        StringBuilder sb = new StringBuilder();
                        for (byte b : macBytes) {
                            sb.append(String.format("%02x:", b));
                        }
                        return sb.substring(0, sb.length() - 1);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "02:00:00:00:00:00";
        }

        public static String getMacAddressByInetAddress() {
            try {
                InetAddress inetAddress = getInetAddress();
                if (inetAddress != null) {
                    NetworkInterface ni = NetworkInterface.getByInetAddress(inetAddress);
                    if (ni != null) {
                        byte[] macBytes = ni.getHardwareAddress();
                        if (macBytes != null && macBytes.length > 0) {
                            StringBuilder sb = new StringBuilder();
                            for (byte b : macBytes) {
                                sb.append(String.format("%02x:", b));
                            }
                            return sb.substring(0, sb.length() - 1);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "02:00:00:00:00:00";
        }

        public static InetAddress getInetAddress() {
            try {
                Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
                while (nis.hasMoreElements()) {
                    NetworkInterface ni = nis.nextElement();
                    // To prevent phone of xiaomi return "10.0.2.15"
                    if (!ni.isUp()) continue;
                    Enumeration<InetAddress> addresses = ni.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        InetAddress inetAddress = addresses.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            String hostAddress = inetAddress.getHostAddress();
                            if (hostAddress.indexOf(':') < 0) return inetAddress;
                        }
                    }
                }
            } catch (SocketException e) {
                e.printStackTrace();
            }
            return null;
        }

        public static String getMacAddressByFile() {
            ShellUtils.CommandResult result = ShellUtils.execCmd("getprop wifi.interface", false);
            if (result.result == 0) {
                String name = result.successMsg;
                if (name != null) {
                    result = ShellUtils.execCmd("cat /sys/class/net/" + name + "/address", false);
                    if (result.result == 0) {
                        String address = result.successMsg;
                        if (address != null && address.length() > 0) {
                            return address;
                        }
                    }
                }
            }
            return "02:00:00:00:00:00";
        }
    }
}
