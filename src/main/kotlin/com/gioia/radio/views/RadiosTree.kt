package com.gioia.radio.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.bonsai.core.Bonsai
import cafe.adriel.bonsai.core.node.Branch
import cafe.adriel.bonsai.core.node.Leaf
import cafe.adriel.bonsai.core.tree.Tree
import com.gioia.radio.examplesToDelete.EmojiIcon

@Composable
fun RadiosTree(
    modifier: Modifier
){
    val tree = Tree<String> {
        // fixme: Cambiar el ícono a cada país. Guardarlo en la bd.
        Branch("Argentina", customIcon = { EmojiIcon("🐺") }) {
            Leaf("La cien")
            Leaf("Radio aspen")
        }
        Branch("Uruguay", customIcon = { EmojiIcon("🐺") }) {
            Leaf("Radio 1")
            Leaf("Radio 2")
        }
    }

    Bonsai(modifier = modifier, tree = tree)
}