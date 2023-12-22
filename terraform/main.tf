resource "aws_sns_topic" "my_topic" {
  name = "product-events"
}

# Create an SQS queue
resource "aws_sqs_queue" "my_queue" {
  name = "product-events"
}

# Subscribe the queue to the topic
resource "aws_sns_topic_subscription" "my_subscription" {
  topic_arn = aws_sns_topic.my_topic.arn
  protocol  = "sqs"
  endpoint  = aws_sqs_queue.my_queue.arn
}