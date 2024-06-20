package RecyclerViewHelpers

import android.view.TextureView
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fernando_hernandes_ticketslab.R

class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
    //mando a llamar a lo de la card
    val txtNombreCard = view.findViewById<TextView>(R.id.txtNombreCard)
}