package novice.present.domain;

import lombok.Data;

/*
*
* 성별, 나이, 관계, 금액대, 상황(option), 카테고리(option), 자유 조건(option)
* 금액대도 그대로 받기보단 어떠한 포멧을 만드는게 좋아보임
*/

@Data
public class ItemInfo {
    //*임시* 성별은 2개밖에 없으므로 추후에 html에서 radio나 콤보박스같은 형식으로 받을 예정
    private String gender;
    private int age;
    private int price;
    private String situation;
    private String category;
    private String additionalOption;

}
