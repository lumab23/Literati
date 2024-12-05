package com.aula.literatiapp.presentation.screens.chat.viewModels

import androidx.lifecycle.ViewModel
import com.aula.literatiapp.domain.model.Message
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ConversationViewModel @Inject constructor(): ViewModel(){
    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages = _messages.asStateFlow()
    private val db = Firebase.database

    fun sendMessage(channelID: String, messageText: String){
        val message = Message(
            id = db.reference.push().key ?: UUID.randomUUID().toString(),
            senderId = Firebase.auth.currentUser?.uid ?: "",
            message = messageText,
            createdAt = System.currentTimeMillis(),
            senderName = Firebase.auth.currentUser?.displayName ?: "",
            senderImage = null,
            imageUrl = null)
        db.reference.child("messages").child(channelID).push().setValue(message)
    }

    fun listenForMessages(channelID: String){
        db.getReference("message")
            .child(channelID)
            .orderByChild("createdAt")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = mutableListOf<Message>()
                    snapshot.children.forEach{ data ->
                        val message = data.getValue(Message::class.java)
                        message?.let {
                            list.add(it)
                        }
                    }
                    _messages.value = list
                }
                override fun onCancelled(error: DatabaseError) {
                    // Trate o erro aqui (opcional)
                    println("Error fetching messages: ${error.message}")
                }
            })
    }
}