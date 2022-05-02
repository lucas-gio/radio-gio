package com.gioia.radio.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.bonsai.core.Bonsai
import cafe.adriel.bonsai.core.node.Branch
import cafe.adriel.bonsai.core.node.Leaf
import cafe.adriel.bonsai.core.tree.Tree
import com.gioia.radio.config.di
import com.gioia.radio.data.domains.Country
import com.gioia.radio.data.domains.Radio
import org.dizitart.no2.FindOptions
import org.dizitart.no2.Nitrite
import org.dizitart.no2.SortOrder
import org.kodein.di.instance

@Composable
fun RadiosTree(
    modifier: Modifier
){
    val tree = Tree<String> {
        val database: Nitrite by di.instance() //fixme pasar a arquitectura.

        database
            .getRepository(Country::class.java)
            ?.find(
                FindOptions
                .sort("name", SortOrder.Ascending) //fixme:Reemplazar con constante.
                .thenLimit(0, 5)
            )
            ?.forEach { country : Country ->
                // fixme: Cambiar el ícono a cada país. Guardarlo en la bd.
                Branch(country.name) {
                    country
                        .radios
                        ?.forEach { radio: Radio ->
                            Leaf(radio.name)
                        }
                }
            }
    }

    Bonsai(modifier = modifier, tree = tree)
}