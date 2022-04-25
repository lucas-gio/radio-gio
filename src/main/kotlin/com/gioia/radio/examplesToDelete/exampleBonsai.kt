package com.gioia.radio.examplesToDelete

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import cafe.adriel.bonsai.core.Bonsai
import cafe.adriel.bonsai.core.node.Branch
import cafe.adriel.bonsai.core.node.Leaf
import cafe.adriel.bonsai.core.tree.Tree

@Composable
fun EmojiIcon(emoji: String) {
    Text(emoji)
}

@Composable
fun BonsaiExample() {
    val tree = Tree<String> {
        Branch("Mammalia") {
            Branch("Carnivora") {
                Branch("Canidae") {
                    Branch("Canis") {
                        Leaf("Wolf", customIcon = { EmojiIcon("🐺") })
                        Leaf("Dog", customIcon = { EmojiIcon("🐶") })
                    }
                }
                Branch("Felidae") {
                    Branch("Felis") {
                        Leaf("Cat", customIcon = { EmojiIcon("🐱") })
                    }
                    Branch("Panthera") {
                        Leaf("Lion", customIcon = { EmojiIcon("🦁") })
                    }
                }
            }
        }
    }

    Bonsai(tree)
}

@Composable
fun test() {
    BonsaiExample()
}