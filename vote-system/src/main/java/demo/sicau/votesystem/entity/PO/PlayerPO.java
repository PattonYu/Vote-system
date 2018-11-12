package demo.sicau.votesystem.entity.PO;

/**
 * @author PattonYu
 * @date 2018/11/6 23:02
 * Description:
 */
public class PlayerPO {
    private String id;

    private String realName;

    private String profile;

    private int votes;

    public PlayerPO() {
    }

    public PlayerPO(String id, String realName, String profile, int votes) {
        this.id = id;
        this.realName = realName;
        this.profile = profile;
        this.votes = votes;
    }

    public PlayerPO(String id, String realName, String profile) {
        this.id = id;
        this.realName = realName;
        this.profile = profile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public void voteAdd(){
        this.votes++;
    }

    @Override
    public String toString() {
        return "PlayerPO{" +
                "id='" + id + '\'' +
                ", realName='" + realName + '\'' +
                ", profile='" + profile + '\'' +
                ", votes=" + votes +
                '}';
    }

}
