import java.lang.RuntimeException

class NoteService {
    private var notes = mutableListOf<Notes>()
    private var comments = mutableListOf<Comments>()

    fun addNote(note: Notes): Int {
        var newId = notes.size + 1
        notes += note.copy(id = newId)
        return notes.last().id
    }

    fun createComment(noteId: Int, comment: Comments): Int {
        var newId = comments.size + 1
        for ((index) in notes.withIndex()) {
            if (notes[index].id == noteId) {
                comments += comment.copy(id = newId, noteId = notes[index].id)
                return comments.last().id
            }
        }
        throw NoteNotFoundException()
    }

    fun deleteNote(noteId: Int): Int {
        for ((index) in notes.withIndex()) {
            if (notes[index].id == noteId) {
                for ((index) in comments.withIndex()) {
                    if (comments[index].noteId == noteId) {
                        deleteComment(comments[index].id)
                    }
                }
                notes.removeAt(index)
                println("Заметка удалена")
                return 1
            }
        }
        throw NoteNotFoundException()
    }

    fun deleteComment(commentId: Int): Int {
        for ((index) in comments.withIndex()) {
            if (comments[index].id == commentId) {
                comments[index] = comments[index].copy(isDelete = true)
                println("Коментарий удалён!")
                return 1
            }
        }
        throw CommentNotFoundException()
    }

    fun editNote(noteId: Int, text: String): Int {
        for ((index) in notes.withIndex()) {
            if (notes[index].id == noteId) {
                notes[index] = notes[index].copy(text = text)
                println("Текст заметки изменён")
                return 1
            }
        }
        throw NoteNotFoundException()
    }

    fun editComment(commentId: Int, message: String): Int {
        for ((index) in comments.withIndex()) {
            if (comments[index].isDelete) {
                throw CommentNotFoundException()
            } else {
                if (comments[index].id == commentId) {
                    comments[index] = comments[index].copy(message = message)
                    println("Комментарий изменён")
                    return 1
                }
            }
        }
        throw CommentNotFoundException()
    }

    fun getNotes(): List<Notes> {
        if (notes.isNotEmpty()) return notes else throw NoteNotFoundException()

    }

    fun getNoteById(noteId: Int): Notes {
        for ((index) in notes.withIndex()) {
            if (notes[index].id == noteId) {
                return notes[index]
            }
        }
        throw NoteNotFoundException()
    }

    fun getComments(noteId: Int): ArrayList<Comments> {
        var commentsArray = arrayListOf<Comments>()
        for ((index) in comments.withIndex()) {
            if (comments[index].noteId == noteId) commentsArray += comments[index]
        }
        if (commentsArray.isEmpty()) throw CommentNotFoundException() else return commentsArray
    }


    fun restoreComment(commentId: Int): Int {
        for ((index) in comments.withIndex()) {
            if (comments[index].id == commentId) {
                if (comments[index].isDelete) {
                    comments[index] = comments[index].copy(isDelete = false)
                    println("Коментарий восстановлен!")
                } else throw CommentNotFoundException()
            }
        }
        return 1
    }
}

class NoteNotFoundException(message: String = "Заметка не найдена!") : RuntimeException(message)
class CommentNotFoundException(message: String = "Комментарий не найден!") : RuntimeException(message)
