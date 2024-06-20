package RecyclerViewHelpers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fernando_hernandes_ticketslab.R
import modelo.tbTickets

class Adaptador (var Datos:List<tbTickets>):RecyclerView.Adapter<ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //unir el rcv con la card
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_card,parent,false )
        return ViewHolder(vista)
    }

    override fun getItemCount() = Datos.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //controlar la card
        val item = Datos[position]
        holder.txtNombreCard.text = item.tituloTicket
    }


}