package spring.backend.library.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseDTO {
  @JsonInclude(Include.NON_NULL)
  @ApiModelProperty(hidden = true)
  private Long id;

  @JsonInclude(Include.NON_NULL)
  @ApiModelProperty(hidden = true)
//  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private ZonedDateTime createdAt;

  @JsonInclude(Include.NON_NULL)
  @ApiModelProperty(hidden = true)
  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private ZonedDateTime updatedAt;

  @JsonInclude(Include.NON_NULL)
  @ApiModelProperty(hidden = true)
  private Long createdBy;

  @JsonInclude(Include.NON_NULL)
  @ApiModelProperty(hidden = true)
  private Long updatedBy;
}
