package by.verkpavel.grafolnet.model

trait Model {
  private val items =
    ImageResponse(1, Map("foo" -> "More information about Foo")) ::
      ImageResponse(2, Map("bar" -> "More information about Bar")) ::
      ImageResponse(3, Map("qux" -> "More information about Qux")) ::
      ImageResponse(4, Map("quux" -> "More information about Quux")) ::
      ImageResponse(5, Map("quuux" -> "More information about Quuux")) ::
      ImageResponse(6, Map("quuuгx" -> "More information about Quгuux")) ::
      Nil

  def get(id: Int) = items find (_.id == id)

  def list = items

}