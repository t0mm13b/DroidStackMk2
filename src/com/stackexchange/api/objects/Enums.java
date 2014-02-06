package com.stackexchange.api.objects;

import com.google.gson.annotations.SerializedName;

public class Enums {
	/***
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum PostType{
		@SerializedName("question") Question("question"),
		@SerializedName("answer") Answer("answer"),
		Unknown("unknown");
		private String mFriendlyName;
		private PostType(String name){this.mFriendlyName = name; }
		public String toString(){ return this.mFriendlyName; }
	}
	
	/***
	 * We're not touching this, as that is designated by site admins.
	 * @author t0mm13b
	 *
	 */
	public static enum UserType{
		@SerializedName("unregistered") Unregistered("unregistered"),
		@SerializedName("registered") Registered("registered"),
		@SerializedName("moderator") Moderator("moderator"),
		@SerializedName("does_not_exist") DoesNotExist("does_not_exist"),
		Unknown("Unknown");
		private String mFriendlyName;
		private UserType(String name){this.mFriendlyName = name; }
		public String toString(){ return this.mFriendlyName; }
	}
	
	/***
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum BadgeType{
		@SerializedName("named") Named("named"),
		@SerializedName("tag_based") TagBased("tag_based"),
		Unknown("Unknown");
		private String mFriendlyName;
		private BadgeType(String name){this.mFriendlyName = name; }
		public String toString(){ return this.mFriendlyName; }
	}
	
	/***
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum BadgeRank{
		@SerializedName("gold") Gold("gold"),
		@SerializedName("silver") Silver("silver"),
		@SerializedName("bronze") Bronze("bronze"),
		Unknown("unknown");
		private String mFriendlyName;
		private BadgeRank(String name){this.mFriendlyName = name; }
		public String toString(){ return this.mFriendlyName; }
	}
	
	/***
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum Relation{
		@SerializedName("parent") Parent("parent"),
		@SerializedName("meta") Meta("meta"),
		@SerializedName("chat") Chat("chat"),
		Unknown("unknown");
		private String mFriendlyName;
		private Relation(String name){this.mFriendlyName = name; }
//		private static final Map<String, Relation> strToEnum = new HashMap<String, Relation>();
//		static{
//			for(Relation rel : values()){
//				strToEnum.put(rel.toString(), rel);
//			}
//			//intToEnum.putAll(EnumSet.A)
//		}
//		// faster lookup!
//		public static Relation valueOf(String value){
//			/*for(Relation rel : values()){
//				if (rel.mValue == value) return rel;
//			}
//			return Unknown;*/
//			if (strToEnum.containsKey(value) return intToEnum.get(Integer.valueOf(value));
//			return Unknown;
//		}
		
		public String toString(){ return this.mFriendlyName; }
	}
	
	/***
	 * We're not touching this, as that is designated by site admins.
	 * @author t0mm13b
	 *
	 */
	public static enum SiteState{
		@SerializedName("normal") Normal,
		@SerializedName("closed_beta") ClosedBeta,
		@SerializedName("open_beta") OpenBeta,
		@SerializedName("linked_meta") LinkedMeta,
		Unknown
	}
	
	/***
	 * We're not touching this, as that is designated by site admins.
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum SiteType{
		@SerializedName("main_site") MainSite,
		@SerializedName("meta_site") MetaSite,
		Unknown
	}
	
	/***
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum EventType{
		@SerializedName("question_posted") QuestionPosted("question_posted"),
		@SerializedName("answer_posted") AnswerPosted("answer_posted"),
		@SerializedName("comment_posted") CommentPosted("comment_posted"),
		@SerializedName("post_edited") PostEdited("post_edited"),
		@SerializedName("user_created") UserCreated("user_created"),
		Unknown("unknown");
		private String mFriendlyName;
		private EventType(String name){this.mFriendlyName = name; }
		public String toString(){ return this.mFriendlyName; }
	}
	
	/***
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum InboxType{
		@SerializedName("comment") Comment("comment"),
		@SerializedName("chat_message") ChatMessage("chat_message"),
		@SerializedName("new_answer") NewAnswer("new_answer"),
		@SerializedName("careers_message") CareersMessage("careers_message"),
		@SerializedName("careers_invitation") CareersInvitation("careers_invitation"),
		@SerializedName("meta_question") MetaQuestion("meta_question"),
		@SerializedName("post_notice") PostNotice("post_notice"),
		@SerializedName("moderator_message") ModeratorMessage("moderator_message"),
		Unknown("unknown");
		private String mFriendlyName;
		private InboxType(String name){this.mFriendlyName = name; }
		public String toString(){ return this.mFriendlyName; }
	}
	
	/***
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum NotificationType{
		@SerializedName("generic") Generic("generic"),
		@SerializedName("profile_activity") ProfileActivity("profile_activity"),
		@SerializedName("bounty_expired") BountyExpired("bounty_expired"),
		@SerializedName("bounty_expires_in_one_day") BountyExpires1D("bounty_expires_in_one_day"),
		@SerializedName("badge_earned") BadgeEarned("badge_earned"),
		@SerializedName("bounty_expires_in_three_days") BountyExpires3D("bounty_expires_in_three_days"),
		@SerializedName("reputation_bonus") ReputationBonus("reputation_bonus"),
		@SerializedName("accounts_associated") AccountsAssociated("accounts_associated"),
		@SerializedName("new_privilege") NewPrivilege("new_privilege"),
		@SerializedName("post_migrated") PostMigrated("post_migrated"),
		@SerializedName("moderator_message") ModeratorMessage("moderator_message"),
		@SerializedName("registration_reminder") RegistrationReminder("registration_reminder"),
		@SerializedName("edit_suggested") EditSuggested("edit_suggested"),
		@SerializedName("substantive_edit") SubstantiveEdit("substantive_edit"),
		@SerializedName("bounty_grace_period_started") BountyGracePeriodStarted("bounty_grace_period_started"),
		Unknown("unknown");
		private String mFriendlyName;
		private NotificationType(String name){this.mFriendlyName = name; }
		public String toString(){ return this.mFriendlyName; }
	}
	
	/***
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum TimelineType{
		@SerializedName("question") Question("question"),
		@SerializedName("answer") Answer("answer"),
		@SerializedName("comment") Comment("comment"),
		@SerializedName("unaccepted_answer") UnacceptedAnswer("unaccepted_answer"),
		@SerializedName("accepted_answer") AcceptedAnswer("accepted_answer"),
		@SerializedName("vote_aggregate") VoteAggregate("vote_aggregate"),
		@SerializedName("revision") Revision("revision"),
		@SerializedName("post_state_changed") PostStateChanged("post_state_changed"),
		@SerializedName("commented") Commented("commented"),
		@SerializedName("asked") Asked("asked"),
		@SerializedName("answered") Answered("answered"),
		@SerializedName("badge") Badge("badge"),
		@SerializedName("accepted") Accepted("accepted"),
		@SerializedName("reviewed") Reviewed("reviewed"),
		@SerializedName("suggested") Suggested("suggested"),
		Unknown("unknown");
		private String mFriendlyName;
		private TimelineType(String name){this.mFriendlyName = name; }
		public String toString(){ return this.mFriendlyName; }
	}
	
	/***
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum VoteType{
		@SerializedName("accepts") Accepts("accepts"),
		@SerializedName("up_votes") UpVotes("up_votes"),
		@SerializedName("down_votes") DownVotes("down_votes"),
		@SerializedName("bounties_offered") BountiesOffered("bounties_offered"),
		@SerializedName("bounties_won") BountiesWon("bounties_won"),
		@SerializedName("spam") Spam("spam"),
		@SerializedName("suggested_edits") SuggestedEdits("suggested_edits"),
		Unknown("unknown");
		private String mFriendlyName;
		private VoteType(String name){this.mFriendlyName = name; }
		public String toString(){ return this.mFriendlyName; }
	}
	
	/***
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum ReputationHistoryType{
		@SerializedName("asker_accepts_answer") AskerAcceptsAnswer("asker_accepts_answer"),
		@SerializedName("asker_unaccept_answer") AskerUnacceptAnswer("asker_unaccept_answer"),
		@SerializedName("answer_accepted") AnswerAccepted("answer_accepted"),
		@SerializedName("answer_unaccepted") AnswerUnaccepted("answer_unaccepted"),
		@SerializedName("voter_downvotes") VoterDownVotes("voter_downvotes"),
		@SerializedName("voter_undownvotes") VoterUndownVotes("voter_undownvotes"),
		@SerializedName("post_downvoted") PostDownvoted("post_downvoted"),
		@SerializedName("post_undownvoted") PostUndownVoted("post_undownvoted"),
		@SerializedName("post_upvoted") PostUpvoted("post_upvoted"),
		@SerializedName("post_unupvoted") PostUnupVoted("post_unupvoted"),
		@SerializedName("suggested_edit_approval_received") SuggestedEditApprovalReceived("suggested_edit_approval_received"),
		@SerializedName("post_flagged_as_spam") PostFlaggedAsSpam("post_flagged_as_spam"),
		@SerializedName("post_flagged_as_offensive") PostFlaggedAsOffensive("post_flagged_as_offensive"),
		@SerializedName("bounty_given") BountyGiven("bounty_given"),
		@SerializedName("bounty_earned") BountyEarned("bounty_earned"),
		@SerializedName("bounty_cancelled") BountyCancelled("bounty_cancelled"),
		@SerializedName("post_deleted") PostDeleted("post_deleted"),
		@SerializedName("post_undeleted") PostUndeleted("post_undeleted"),
		@SerializedName("association_bonus") AssociationBonus("association_bonus"),
		@SerializedName("arbitrary_reputation_change") ArbitraryReputationChange("arbitrary_reputation_change"),
		@SerializedName("vote_fraud_reversal") VoteFraudReversal("vote_fraud_reversal"),
		@SerializedName("post_migrated") PostMigrated("post_migrated"),
		@SerializedName("user_deleted") UserDeleted("user_deleted"),
		Unknown("unknown");
		private String mFriendlyName;
		private ReputationHistoryType(String name){this.mFriendlyName = name; }
		public String toString(){ return this.mFriendlyName; }
	}
	
	/***
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum RevisionType{
		@SerializedName("single_user") SingleUser("single_user"),
		@SerializedName("vote_based") VoteBased("vote_based"),
		Unknown("Unknown");
		private String mFriendlyName;
		private RevisionType(String name){this.mFriendlyName = name; }
		public String toString(){ return this.mFriendlyName; }
	}
	
	/***
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum FilterType{
		SingleUser("safe"),
		VoteBased("unsafe"),
		Invalid("invalid"),
		Unknown("Unknown");
		private String mFriendlyName;
		private FilterType(String name){this.mFriendlyName = name; }
		public String toString(){ return this.mFriendlyName; }
	}

	/***
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum SortType{
		Activity("activity"),
		Creation("creation"),
		Votes("votes"),
		Rank("rank"), // For Badges
		Name("name"), // For Badges, Tags
		Type("type"), // For Badges
		Approval("approval"), // Post/Suggested Edits
		Rejection("rejection"), // Post/Suggested Edits
		Hot("hot"), // Questions
		Week("week"), // Questions
		Month("month"), // Questions
		Popular("popular"), // Tags
		Reputation("reputation"), // Users
		Modified("modified"), // Users
		Added("added"), // Users
		Relevance("relevance"), // Search
		Unknown("Unknown");
		private String mFriendlyName;
		private SortType(String name){this.mFriendlyName = name; }
		public String toString(){ return this.mFriendlyName; }
	}
	
	/***
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum SortOrder{
		@SerializedName("desc") Desc("desc"),
		@SerializedName("asc") Asc("asc"),
		Unknown("unknown");
		private String mFriendlyName;
		private SortOrder(String name){this.mFriendlyName = name; }
		public String toString(){ return this.mFriendlyName; }
	}
	
	/***
	 * 
	 * @author t0mm13b
	 *
	 */
	public static enum Period{
		@SerializedName("all_time") AllTime("all_time"),
		@SerializedName("month") Month("month"),
		Unknown("unknown");
		private String mFriendlyName;
		private Period(String name){this.mFriendlyName = name; }
		public String toString(){ return this.mFriendlyName; }
	}
}
