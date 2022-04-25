package com.gioia.radio.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.bonsai.core.node.Branch
import cafe.adriel.bonsai.core.node.Leaf
import cafe.adriel.bonsai.core.tree.Tree
import com.gioia.radio.examplesToDelete.EmojiIcon

@Composable
fun RadiosTree(
    modifier: Modifier
){
    Tree<String> {
        Branch("Argentina") {
            Leaf("La cien", customIcon = { EmojiIcon("🐺") })
            Leaf("Radio aspen", customIcon = { EmojiIcon("🐶") })
        }
        Branch("Uruguay") {
            Leaf("Radio 1", customIcon = { EmojiIcon("🐺") })
            Leaf("Radio 2", customIcon = { EmojiIcon("🐶") })
        }
    }
}