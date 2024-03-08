package com.particeep.api.models

case class TableSearch(
  custom_field:  Option[String] = None,
  custom_value:  Option[String] = None,
  sort_by:       Option[String] = None,
  order_by:      Option[String] = Some("asc"),
  global_search: Option[String] = None,
  limit:         Option[Int]    = Some(30),
  offset:        Option[Int]    = Some(0)
)
