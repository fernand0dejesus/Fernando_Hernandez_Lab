package com.example.fernando_hernandes_ticketslab

import RecyclerViewHelpers.Adaptador
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.ClaseConexion
import modelo.tbTickets
import java.util.UUID

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //llamo to/do
       val txtTitulo = findViewById<EditText>(R.id.txtTituloTicket)
        val txtDescripcion = findViewById<EditText>(R.id.txtDescripcionTicket)
        val txtAutor = findViewById<EditText>(R.id.txtAutorTicket)
        val txtEmail = findViewById<EditText>(R.id.txtEmail)
        val txtEstado = findViewById<EditText>(R.id.txtEstado)
        val btnAgregar = findViewById<Button>(R.id.btnAgregar)
        val rcvTickets =findViewById<RecyclerView>(R.id.rcvTickets)

        //agrego un layout a mi Recycler view
        rcvTickets.layoutManager= LinearLayoutManager(this)
        //////// TO: mostrar datos

        fun obtenerTickets(): List<tbTickets>{

            //1-creo un obheto de la clase conexion
            val objConexion = ClaseConexion().cadenaConxion()

            //creo un statement
            val statement = objConexion?.createStatement()
            val resulSet = statement?.executeQuery("SELECT * FROM tbTickets")!!

            val listaTickets = mutableListOf<tbTickets>()

            while (resulSet.next()){
                val uuid = resulSet.getString("uuid")
                val TITULO_TICKET = resulSet.getString("TITULO_TICKET")
                val DESCRIPCION_TICKET = resulSet.getString("DESCRIPCION_TICKET")
                val AUTOR_TICKET = resulSet.getString("AUTOR_TICKET")
                val EMAIL_CONTACTO_AUTOR = resulSet.getString("EMAIL_CONTACTO_AUTOR")
                val ESTADO_TICKET = resulSet.getString("ESTADO_TICKET")

                val valoresJuntos = tbTickets(uuid,TITULO_TICKET,DESCRIPCION_TICKET,AUTOR_TICKET,EMAIL_CONTACTO_AUTOR,ESTADO_TICKET)


                listaTickets.add(valoresJuntos)

            }
            return  listaTickets

        }
        //asignarle el adaptador al RecyclerView
        CoroutineScope(Dispatchers.IO).launch {
            val ticketsDB = obtenerTickets()
            withContext(Dispatchers.Main){
                val adapter = Adaptador(ticketsDB)
                rcvTickets.adapter = adapter
            }
        }





        //btn Agregar

        btnAgregar.setOnClickListener() {
            CoroutineScope(Dispatchers.IO).launch {
                //1- crear objeto en conexion
                val objConexion = ClaseConexion().cadenaConxion()

                //2-Crear una variable de preparestatement
                val addTicket = objConexion?.prepareStatement( "insert into tbTickets(uuid,TITULO_TICKET,DESCRIPCION_TICKET,AUTOR_TICKET,EMAIL_CONTACTO_AUTOR,ESTADO_TICKET)values(?,?,?,?,?,?)")!!
                addTicket.setString(1, UUID.randomUUID().toString())
                addTicket.setString(2, txtTitulo.text.toString())
                addTicket.setString(3,txtDescripcion.text.toString())
                addTicket.setString(4,txtAutor.text.toString())
                addTicket.setString(5,txtEmail.text.toString())
                addTicket.setString(6,txtEstado.text.toString())
                addTicket.executeUpdate()

            }
        }
    }
}