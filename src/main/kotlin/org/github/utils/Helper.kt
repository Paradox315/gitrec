package org.github.utils

class Helper {
    companion object {
        fun getOffset(page: Int, size: Int): Pair<Int, Int> {
            return Pair(size, (page - 1) * size)
        }
    }
}