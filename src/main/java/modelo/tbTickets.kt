package modelo

data class tbTickets(
    val uuid: String,
    val tituloTicket: String,
    val descripcionTicket: String,
    val autorTicket: String,
    val emailContactoAutor: String,
    val estadoTicket: String
)
