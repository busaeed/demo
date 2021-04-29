CREATE VIEW last_post AS
SELECT * from (
    SELECT 
        topic.id topicId,
        topic.title topicTitle,
    	member.id memberId,
    	member.username username,
        topic.createdAt lastPostDateTime,
        topic.forum_id forum_id,
        null replyId,
        1 pageNumber,
    	IF(LENGTH(topic.title)>25, CONCAT(SUBSTRING(topic.title, 1, 22), '...'), topic.title) shortTopicTitle
    FROM topic
    LEFT JOIN member ON (topic.member_id = member.id)
    UNION
    SELECT
        reply1.topic_id,
        topic1.title,
        member.id,
    	member.username,
        reply1.createdAt,
        topic1.forum_id,
        reply1.id,
        CEIL((SELECT COUNT(*) FROM reply WHERE topic_id = reply1.topic_id)/10),
    	IF(LENGTH(topic1.title)>25, CONCAT(SUBSTRING(topic1.title, 1, 22), '...'), topic1.title) shortTopicTitle
    FROM reply reply1
    LEFT JOIN topic topic1 ON (reply1.topic_id = topic1.id)
    LEFT JOIN member ON (reply1.member_id = member.id)
    ORDER BY lastPostDateTime DESC
) theTable
GROUP BY theTable.forum_id;

INSERT INTO role VALUES (1, 'ROLE_USER');
UPDATE role_seq SET next_val= 2;