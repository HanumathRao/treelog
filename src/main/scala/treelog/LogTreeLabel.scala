package treelog

sealed trait LogTreeLabel[+R] {
  def references: R
  def success(): Boolean
  def fold[T](f: DescribedLogTreeLabel[R] ⇒ T, g: UndescribedLogTreeLabel[R] ⇒ T): T
}

case class DescribedLogTreeLabel[+R](description: String, success: Boolean, references: R) extends LogTreeLabel[R] {
  def fold[T](f: DescribedLogTreeLabel[R] ⇒ T, g: UndescribedLogTreeLabel[R] ⇒ T) = f(this)
}

case class UndescribedLogTreeLabel[+R](success: Boolean, references: R) extends LogTreeLabel[R] {
  def fold[T](f: DescribedLogTreeLabel[R] ⇒ T, g: UndescribedLogTreeLabel[R] ⇒ T) = g(this)
}