package novice.present.domain;

import lombok.Data;

/*
*
* 성별, 나이, 관계, 금액대, 상황(option), 카테고리(option), 자유 조건(option)
* 금액대도 그대로 받기보단 어떠한 포멧을 만드는게 좋아보임
* id는 item을 구분하기 위해 도입 uuid 사용
*/

@Data
public class ItemInfo {
    //*임시* 성별은 2개밖에 없으므로 추후에 html에서 radio나 콤보박스같은 형식으로 받을 예정
    private String id;
    private String gender;
    private Integer age;
    private String relationship;
    private Integer price;
    private String situation;
    private String category;
    private String option;

    public ItemInfo() {
    }

    public ItemInfo(String id, String gender, int age, String relationship, int price) {
        this.id = id;
        this.gender = gender;
        this.age = age;
        this.relationship = relationship;
        this.price = price;
    }
}
