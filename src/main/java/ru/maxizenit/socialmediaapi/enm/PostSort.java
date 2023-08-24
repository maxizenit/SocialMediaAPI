package ru.maxizenit.socialmediaapi.enm;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

/** Enum, в котором находятся варианты сортировки ленты постов. */
@Getter
@RequiredArgsConstructor
public enum PostSort {

  /** Сортировка по времени по возрастанию. */
  TIMESTAMP_ASC(Sort.by(Sort.Direction.ASC, "timestamp")),

  /** Сортировка по времени по убыванию. */
  TIMESTAMP_DESC(Sort.by(Sort.Direction.DESC, "timestamp"));

  /** Опция сортировки для запросов. */
  private final Sort sortValue;
}
