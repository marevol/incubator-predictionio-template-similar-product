package org.example.similarproduct

import org.apache.predictionio.controller.EngineFactory
import org.apache.predictionio.controller.Engine

case class Query(
  items: List[String],
  num: Int,
  categories: Option[Set[String]],
  categoryBlackList: Option[Set[String]],
  whiteList: Option[Set[String]],
  blackList: Option[Set[String]]
) extends Serializable

case class PredictedResult(
  itemScores: Array[ItemScore]
) extends Serializable {
  override def toString: String = itemScores.mkString(",")
}

case class ItemScore(
  item: String,
  score: Double
) extends Serializable

object SimilarProductEngine extends EngineFactory {
  def apply() = {
    new Engine(
      classOf[DataSource],
      classOf[Preparator],
      Map(
        "als" -> classOf[ALSAlgorithm],
        "cooccurrence" -> classOf[CooccurrenceAlgorithm]),
      classOf[Serving])
  }
}
