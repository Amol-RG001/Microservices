package com.amol.user.service.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    private String ratingId;
    private String userId;
    private String hotelId;
    private float rating;
    private String remark;
    private String feedback;
    private Hotel hotel;
}
