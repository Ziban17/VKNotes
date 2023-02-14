import org.junit.Test
import kotlin.test.assertEquals

class NoteServiceTest {

    @Test
    fun addNote() {
        val service = NoteService()
        val result = service.addNote(Notes())

        assertEquals(1, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun createCommentShouldThrow() {
        val service = NoteService()
        val result = service.createComment(1, Comments())
    }

    @Test
    fun createComment() {
        val service = NoteService()
        val note = service.addNote(Notes())
        val result = service.createComment(note, Comments())

        assertEquals(1, result)
    }

    @Test
    fun deleteNote() {
        val service = NoteService()
        val note = service.addNote(Notes())
        val result = service.deleteNote(note)

        assertEquals(1, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun deleteNoteShouldThrow() {
        val service = NoteService()
        val note = service.addNote(Notes())
        val result = service.deleteNote(2)
    }

    @Test
    fun deleteComment() {
        val service = NoteService()
        val note = service.addNote(Notes())
        val comment = service.createComment(note, Comments())
        val result = service.deleteComment(comment)

        assertEquals(1, result)
    }

    @Test(expected = CommentNotFoundException::class)
    fun deleteCommentShouldThrow() {
        val service = NoteService()
        val note = service.addNote(Notes())
        val result = service.deleteComment(1)
    }


    @Test
    fun editNote() {
        val service = NoteService()
        val note = service.addNote(Notes())
        val result = service.editNote(note, "123")

        assertEquals(1, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun editNoteShouldThrow() {
        val service = NoteService()
        val note = service.addNote(Notes())
        val result = service.editNote(2, "123")
    }

    @Test
    fun editComment() {
        val service = NoteService()
        val note = service.addNote(Notes())
        val comment = service.createComment(note, Comments())
        val result = service.editComment(comment, "Hello World!")

        assertEquals(1, result)
    }

    @Test(expected = CommentNotFoundException::class)
    fun editCommentShouldThrow() {
        val service = NoteService()
        val note = service.addNote(Notes())
        val result = service.editComment(1, "Hello World!")
    }

    @Test(expected = CommentNotFoundException::class)
    fun editCommentShouldNotFoundDeletedComment() {
        val service = NoteService()
        val note = service.addNote(Notes())
        val comment = service.createComment(note, Comments())
        service.deleteComment(comment)
        val result = service.editComment(comment, "Hello World!")
    }

    @Test
    fun getNotes() {
        val service = NoteService()
        service.addNote(Notes())
        val result = service.getNotes()

        assert(result.isNotEmpty())
    }

    @Test(expected = NoteNotFoundException::class)
    fun getNotesShouldThrow() {
        val service = NoteService()
        service.getNotes()
    }

    @Test
    fun getNoteById() {
        val service = NoteService()
        val note = service.addNote(Notes())
        val result = service.getNoteById(note)

        assertEquals(note, result.id)
    }

    @Test(expected = NoteNotFoundException::class)
    fun getNoteByIdShouldThrow() {
        val service = NoteService()
        val result = service.getNoteById(1)
    }


    @Test
    fun getComments() {
        val service = NoteService()
        val note = service.addNote(Notes())
        service.createComment(note, Comments())
        service.createComment(note, Comments())
        val result = service.getComments(note)

        assert(result.isNotEmpty())
    }

    @Test(expected = CommentNotFoundException::class)
    fun getCommentsShouldThrow() {
        val service = NoteService()
        val note = service.addNote(Notes())
        service.getComments(note)
    }


    @Test
    fun restoreComment() {
        val service = NoteService()
        val note = service.addNote(Notes())
        val comment = service.createComment(note, Comments())
        service.deleteComment(comment)
        service.restoreComment(comment)

        assertEquals(1, comment)
    }

    @Test(expected = CommentNotFoundException::class)
    fun restoreCommentShouldThrow() {
        val service = NoteService()
        val note = service.addNote(Notes())
        val comment = service.createComment(note, Comments())
        service.restoreComment(comment)
    }
}