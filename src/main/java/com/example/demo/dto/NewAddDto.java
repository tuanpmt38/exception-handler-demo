package com.example.demo.dto;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewAddDto implements Serializable {

  @NotBlank(message = "err.new.title-is-mandatory")
  private String title;

  private String content;

  private String imageUrl;

  private String description;

}
