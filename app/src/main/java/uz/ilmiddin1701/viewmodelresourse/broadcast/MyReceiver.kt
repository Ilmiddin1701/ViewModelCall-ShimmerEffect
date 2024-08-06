package uz.ilmiddin1701.viewmodelresourse.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import uz.ilmiddin1701.viewmodelresourse.utils.MyData
import uz.ilmiddin1701.viewmodelresourse.utils.NetworkHelper

class MyReceiver : BroadcastReceiver() {

    private lateinit var networkHelper: NetworkHelper
    override fun onReceive(context: Context, intent: Intent) {
        networkHelper = NetworkHelper(context)
        MyData.internetLiveData.postValue(networkHelper.isNetworkConnected())
    }
}