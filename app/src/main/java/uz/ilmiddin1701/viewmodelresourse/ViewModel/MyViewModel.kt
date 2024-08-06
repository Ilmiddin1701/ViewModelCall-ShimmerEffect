package uz.ilmiddin1701.viewmodelresourse.ViewModel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.ilmiddin1701.viewmodelresourse.models.MyMoney
import uz.ilmiddin1701.viewmodelresourse.models.Resource
import uz.ilmiddin1701.viewmodelresourse.retrofit.ApiClient
import uz.ilmiddin1701.viewmodelresourse.utils.MyData

class MyViewModel: ViewModel() {

    private val liveMoney = MutableLiveData<Resource<ArrayList<MyMoney>>>()

    fun getList(context: LifecycleOwner): MutableLiveData<Resource<ArrayList<MyMoney>>> {
        MyData.internetLiveData.observe(context) {
            if (it) {
                liveMoney.postValue(Resource.loading(null))
                ApiClient.getApiService().getAllMoney().enqueue(object : Callback<List<MyMoney>> {
                    override fun onResponse(p0: Call<List<MyMoney>>, p1: Response<List<MyMoney>>) {
                        if (p1.isSuccessful) {
                            liveMoney.postValue(Resource.success(p1.body() as ArrayList))
                        }
                    }
                    override fun onFailure(p0: Call<List<MyMoney>>, p1: Throwable) {
                        return liveMoney.postValue(Resource.error(p1.message.toString(), null))
                    }
                })
            }
        }
        return liveMoney
    }

}