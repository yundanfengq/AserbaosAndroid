package com.aserbao.aserbaosandroid.AUtils.utils_realize;

import android.bluetooth.BluetoothAdapter;
import android.view.View;

import com.example.base.utils.date.AppScreenMgr;
import com.example.base.utils.date.AppSysMgr;
import com.example.base.utils.network.ANetworkUtils;
import com.example.base.utils.phone.APhoneMediaUtils;
import com.example.base.utils.phone.APhoneUtils;
import com.example.base.utils.screen.AScreenUtils;
import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;

public class AUtilsRealizeActivity extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("获取当前手机信息",0));
        mBaseRecyclerBean.add(new BaseRecyclerBean("网络状态",1));
        mBaseRecyclerBean.add(new BaseRecyclerBean("多媒体数据获取",2));
        mBaseRecyclerBean.add(new BaseRecyclerBean("屏幕常用参数获取",3));
        mBaseRecyclerBean.add(new BaseRecyclerBean("ContextWrapper的常用方法调用",4));
        mBaseRecyclerBean.add(new BaseRecyclerBean("检测手机NavigationBar的高度",5));
    }


    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        switch (position){
            case 0:
                StringBuffer stringBuffer = new StringBuffer();
                String phoneMeesager = APhoneUtils.getInstance().getPhoneMeesager(this);
                String appSysMgrInfo = AppSysMgr.getAppSysMgrInfo(this);
                stringBuffer.append(phoneMeesager).append(appSysMgrInfo);

                BluetoothAdapter myDevice = BluetoothAdapter.getDefaultAdapter();
                if (myDevice != null) {
                    String deviceName = myDevice.getName();
                    mBaseRecyclerTv.setText(deviceName + stringBuffer.toString());
                }else{
                    mBaseRecyclerTv.setText(stringBuffer.toString());
                }

                break;
            case 1:
                int networkState = ANetworkUtils.getNetworkState(this);
                String valueOf = String.valueOf(networkState);
                mBaseRecyclerTv.setText(valueOf);
                break;
            case 2:
                String phoneMediaInfo = APhoneMediaUtils.getPhoneMediaInfo(this);
                mBaseRecyclerTv.setText(phoneMediaInfo);
                break;
            case 3:
                mBaseRecyclerTv.setText(AppScreenMgr.getScreenInfo(this));
                break;
            case 4:
                mBaseRecyclerTv.setTextSize(16);
                StringBuffer sb = new StringBuffer();
                sb.append("getPackageName() = ").append(getPackageName()).append("\n")
                  .append("getPackageResourcePath() =").append(getPackageResourcePath()).append("\n")
                  .append("getPackageCodePath() =").append(getPackageCodePath()).append("\n")
                  .append("getFilesDir() =").append(getFilesDir().toString()).append("\n");
                mBaseRecyclerTv.setText(sb.toString());
                break;
            case 5:
                mBaseRecyclerTv.setTextSize(16);
                StringBuffer result = new StringBuffer();
                result.append("是否有NavigationBar =" + AScreenUtils.isNavigationBarShowing(mContext))
                    .append("\n 检测到的高度为： "+ AScreenUtils.getNavigationBarHeight(mContext))
                    .append("\n 检测StatusBar的高度为：" + AScreenUtils.getStatusBarHeight(mContext));

                mBaseRecyclerTv.setText(result.toString());
                break;
        }
    }
}
