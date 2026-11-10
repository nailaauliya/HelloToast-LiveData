package lat.pam.hellotoast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NameViewModel : ViewModel() {

    // Menyimpan nilai Count menggunakan LiveData
    val currentName: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

}
