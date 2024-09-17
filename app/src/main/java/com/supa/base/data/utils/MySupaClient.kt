package com.supa.base.data.utils

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable

@Serializable
data class Users(
    val id: String,
    val name: String,
    val email: String,

    )

class MySupaClient {

    private val client = createSupabaseClient(
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImRuYnNqcXNjcXZocGZncGR4em1hIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTcyNjU2NTA5OCwiZXhwIjoyMDQyMTQxMDk4fQ.2jw2TonHK_qbNaycn2kzSrLtOHYVrzvA3MRR9Co1o9o",
        supabaseUrl = "https://dnbsjqscqvhpfgpdxzma.supabase.co",
    ) {
        install(Postgrest)
    }

    suspend fun getAllUsers(): List<Users> {
        return withContext(Dispatchers.IO) {
            client.from("Users").select().decodeList<Users>()
        }
    }


}