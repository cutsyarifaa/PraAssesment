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
import kotlinx.android.synthetic.main.fragment_persegi_panjang.*
import kotlinx.android.synthetic.main.fragment_title.*
import org.d3ifcool4012.myapplication.databinding.FragmentPersegiPanjangBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class PersegiPanjangFragment : Fragment(){
    private var luasPersegi = 0
    private var kelPersegi = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPersegiPanjangBinding>(inflater, R.layout.fragment_persegi_panjang,
            container, false)

        if (savedInstanceState != null){
            luasPersegi = savedInstanceState.getInt("luas")
            kelPersegi = savedInstanceState.getInt("keliling")
        }

        binding.apply {
            hitungPersegiPanjang.setOnClickListener { view: View ->
                var panjang = pPersegiPanjang.text.toString()
                var lebar = lPersegiPanjang.text.toString()

                if (panjang == null && lebar == null) {
                    Toast.makeText(context, "Harap Isi Bidang Ini", Toast.LENGTH_SHORT).show()
                }
                luasPersegi = (panjang.toInt() * lebar.toInt())
                txtLuasPP.text = luasPersegi.toString()

                kelPersegi = (2 * (panjang.toInt() + lebar.toInt()))
                txtKelilingPP.text = kelPersegi.toString()
            }
            sharePersegiPanjang.setOnClickListener {
                var shareIntent = ShareCompat.IntentBuilder.from(requireActivity())
                    .setText(getString(R.string.share_data, luasPersegi, kelPersegi))
                    .setType("text/plain")
                    .intent
                try {
                    startActivity(shareIntent)
                } catch (ex: ActivityNotFoundException) {
                    Toast.makeText(context, "Kosong", Toast.LENGTH_SHORT).show()
                }
            }
            binding.luas1 = luasPersegi
            binding.keliling1 = kelPersegi
            return binding.root
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("luas", luasPersegi)
        outState.putInt("keliling", kelPersegi)
    }
}
