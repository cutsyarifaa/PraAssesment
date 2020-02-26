package org.d3ifcool4012.myapplication


import android.content.ActivityNotFoundException
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.fragment_segitiga.*
import org.d3ifcool4012.myapplication.databinding.FragmentSegitigaBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SegitigaFragment : Fragment() {
    private var lSegitiga = 0.0
    private var kSegitiga = 0.0
    private var sisiMiring = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentSegitigaBinding>(inflater, R.layout.fragment_segitiga,
            container, false)

        if (savedInstanceState != null){
            lSegitiga = savedInstanceState.getDouble("luasnya")
            kSegitiga = savedInstanceState.getDouble("kelilingnya")
        }

        binding.apply {
            hitungSegitiga.setOnClickListener {
                val alas = aSegitiga.text.toString()
                val tinggi = tSegitiga.text.toString()

                lSegitiga = (alas.toDouble() * tinggi.toDouble())/2.0
                txtLuasSegitiga.text = lSegitiga.toString()

                sisiMiring = Math.sqrt(Math.pow(alas.toDouble(), 2.0) + Math.pow(tinggi.toDouble(), 2.0))
                kSegitiga = sisiMiring + alas.toDouble() + tinggi.toDouble()

                txtKelilingSegitiga.text = kSegitiga.toString()
            }

            shareSegitiga.setOnClickListener {
                val shareIntent = ShareCompat.IntentBuilder.from(requireActivity())
                    .setText(getString(R.string.share_data, lSegitiga.toInt(), kSegitiga.toInt()))
                    .setType("text/plain")
                    .intent
                try {
                    startActivity(shareIntent)
                } catch (ex: ActivityNotFoundException) {
                    Toast.makeText(context, "Kosong", Toast.LENGTH_SHORT).show()
                }
            }
            binding.luas2 = lSegitiga
            binding.keliling2 = kSegitiga
            return binding.root
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putDouble("luas", lSegitiga)
        outState.putDouble("keliling", kSegitiga)
        super.onSaveInstanceState(outState)
    }


}
