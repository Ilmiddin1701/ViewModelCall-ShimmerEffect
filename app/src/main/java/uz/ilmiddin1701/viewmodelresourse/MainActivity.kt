package uz.ilmiddin1701.viewmodelresourse

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import uz.ilmiddin1701.viewmodelresourse.ViewModel.MyViewModel
import uz.ilmiddin1701.viewmodelresourse.adapters.RvAdapter
import uz.ilmiddin1701.viewmodelresourse.broadcast.MyReceiver
import uz.ilmiddin1701.viewmodelresourse.databinding.ActivityMainBinding
import uz.ilmiddin1701.viewmodelresourse.models.Status
import uz.ilmiddin1701.viewmodelresourse.utils.MyData

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var myViewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        myViewModel = ViewModelProvider(this)[MyViewModel::class.java]

        val myReceiver = MyReceiver()

        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        this.registerReceiver(myReceiver, filter)

        myViewModel.getList(this).observe(this) {
            when (it.status) {
                Status.Success -> {
                    binding.rv.adapter = RvAdapter(it.data!!)
                    binding.shimmerLayout.stopShimmer()
                    binding.progressBar.visibility = View.GONE
                    binding.shimmerLayout.visibility = View.GONE
                }

                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }

                Status.LOADING -> {
                    binding.shimmerLayout.startShimmer()
                    MyData.internetLiveData.observe(this) { isConnected ->
                        if (!isConnected) {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }

    }
}