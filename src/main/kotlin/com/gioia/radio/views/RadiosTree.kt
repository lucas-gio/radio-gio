package com.gioia.radio.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.bonsai.core.Bonsai
import cafe.adriel.bonsai.core.node.Branch
import cafe.adriel.bonsai.core.node.Leaf
import cafe.adriel.bonsai.core.tree.Tree
import com.gioia.radio.domains.Country
import com.gioia.radio.domains.Radio
import com.gioia.radio.examplesToDelete.EmojiIcon
import com.gioia.radio.singletons.Database
import org.dizitart.no2.FindOptions
import org.dizitart.no2.SortOrder

@Composable
fun RadiosTree(
    modifier: Modifier
){
    val tree = Tree<String> {
        Database
            .getInstance()
            ?.getRepository(Country::class.java)
            ?.find(
                FindOptions
                .sort("name", SortOrder.Ascending) //fixme:Reemplazar con constante.
                .thenLimit(0, 10)
            )
            ?.forEach { country : Country ->
                // fixme: Cambiar el ícono a cada país. Guardarlo en la bd.
                Branch(country.name, customIcon = { EmojiIcon("🐺") }) {
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